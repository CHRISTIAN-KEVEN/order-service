package com.example.orderService.services.Impl;

import com.example.orderService.dtos.requestDtos.AddToCartRequest;
import com.example.orderService.dtos.requestDtos.PlaceOrderRequestDto;
import com.example.orderService.feigns.ProductService;
import com.example.orderService.models.TCart;
import com.example.orderService.models.TCartItem;
import com.example.orderService.models.TOrder;
import com.example.orderService.models.TTmpOrder;
import com.example.orderService.repositories.CartRepository;
import com.example.orderService.repositories.OrderRepository;
import com.example.orderService.repositories.TCartItemRepository;
import com.example.orderService.services.OrderService;
import com.example.orderService.utilities.Constants;
import com.example.orderService.utilities.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    private final TCartItemRepository tCartItemRepository;
    private final CartRepository tCartRepository;

    private OrderRepository orderRepo;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepo, ProductService productService,
                            CartRepository tCartRepository,
                            TCartItemRepository tCartItemRepository) {
        this.orderRepo = orderRepo;
        this.productService = productService;
        this.tCartRepository = tCartRepository;
        this.tCartItemRepository = tCartItemRepository;
    }

    public String addToCart(AddToCartRequest request) {

        JSONObject productData;
        // Call product service to check item validity and availability
        try {
            String productStrResp = productService.getProductDetails(request.getItemId());
            log.info("Response from product service:::ADD-TO-CART {}", productStrResp);
            JSONObject productJSONResp = new JSONObject(productStrResp);
            if(!Constants.PROCESS_SUCCESS.equals(productJSONResp.optString("erc", "0"))) {
                return Utilities.errorMessageReturn("Could not add item to cart. Unable to verify item. Please try again later !");
            }
            productData = productJSONResp.optJSONObject("data");
            if(productData == null) throw new Exception();
            log.info("ITEM DATA::::: {}", productData);

        } catch (Exception ex) {
            log.error("FAILED TO VERIFY PRODUCT ", ex);
            return Utilities.errorMessageReturn("Could not add item to cart. Unable to verify item. Please try again later !");
        }

        // Add item to cart

        TCartItem cartItem = new TCartItem();
        cartItem.setIntQty(request.getQuantity());
        cartItem.setLgItemId(request.getItemId());
        cartItem.setDbPrice(productData.optDouble("price"));
        cartItem.setStrBusinessId(request.getBusinessId());
        cartItem.setBlDeleted(false);
        cartItem.setEmStatus(Constants.CART_ITEM_STATUS.IN_CART.name());
        cartItem.setDtDateCreated(new Date());

        try {
            TCart userCart = tCartRepository.findByStrUserId(request.getUserId());
            if(userCart == null) {
                userCart = createUserCart(request.getUserId());
            }
            cartItem.setLgCartId(userCart.getLgId());

        } catch (Exception ex) {
            log.error("Failed to get user cart", ex);
        }

       try {
           TCartItem savedCartItem = tCartItemRepository.save(cartItem);
           JSONObject cartResp = new JSONObject()
                   .put("item", productData.optString("name"))
                   .put("price", savedCartItem.getDbPrice())
                   .put("qty", savedCartItem.getIntQty());
           return Utilities.dataReturn(cartResp);
       } catch (Exception ex) {
           log.error("Failed to save cart items", ex);
           return Utilities.errorMessageReturn("Unable to add to cart at this time. Please try again later !");
       }

    }

    @Override
    public String placeOrder(PlaceOrderRequestDto request) {

        // Check that stock is available or product is still valid
        // Check payment method
        // If payment method is pay_on_delivery, place order i.e. write a row in t_order
        // Else write the order in t_tmp_order and wait for payment confirmation
        // Initiate a payment confirm quartz task in the back to check payment complete
        // If payment is successful, save the order
        // Update inventory stock

        // Get cart items and check that items can be ordered

          try {
              if(Utilities.isNullOrEmpty(request.getItemsToCheckout())) {
                  log.warn("NO ITEMS SENT TO PLACE ORDER ! {}", request.getItemsToCheckout());
                  return Utilities.errorMessageReturn("NO ITEMS SENT TO PLACE ORDER !");
              }
              List<Long> itemsToCheckout = Arrays.asList(request.getItemsToCheckout().split(","))
                      .stream()
                      .map(Long::parseLong)
                      .collect(Collectors.toList());

              List<TCartItem> cartItems = tCartItemRepository.findByLgId(itemsToCheckout);
              if(Utilities.isNullOrEmpty(cartItems)) {
                  log.warn("NO ITEMS SENT TO PLACE ORDER ! {}", request.getItemsToCheckout());
                  return Utilities.errorMessageReturn("NO ITEMS SENT TO PLACE ORDER !");
              }

              // Check that items are still valid

              String itemIds = cartItems.stream()
                      .map(TCartItem::getLgItemId)
                      .map(String::valueOf)
                      .collect(Collectors.joining(","));

              log.info("item ids {}", itemIds);

//              String productServiceStrResp = productService.getProductsDetails(itemIds);
//              JSONObject pro
//              if(productService != null){
//
//              }

              if(Constants.PaymentMethod.PAY_ON_DELIVERY.name().equalsIgnoreCase(request.getPaymentMethod())) {
                  return effectivelyPlaceOrder(request, cartItems);
              }
              else {
                  // Make call to payment service to trigger payment
                  JSONObject paymentAccount = request.getPaymentAccount();
                  log.info("Payment account {}", paymentAccount);

                  try {
//                      paymentService.initiatePayment(paymentAccount.toString());
                  } catch (Exception ex) {
                      log.error("PAYMENT FAILED ", ex);
                      return Utilities.errorMessageReturn("Payment ");
                  }
                  // Write to temp order
                  TTmpOrder tmpOrder = new TTmpOrder();
                  tmpOrder.setDtCreated(new Date());
                  tmpOrder.setStrItemsToCheckout(request.getItemsToCheckout());
                  tmpOrder.setStrOrderObj(request.toString()); // This might not go as expected
//                  tmpOrder.setStrRef();
              }


          } catch (Exception ex) {
              log.error("FAILED TO PLACE ORDER ", ex);
              return Utilities.errorMessageReturn("FAILED TO PLACE ORDER");
          }


        return Utilities.successMessageReturn("Order placed successfully !");
    }

    public TCart createUserCart(String userId) throws Exception {
        TCart tCart = new TCart();
        tCart.setStrUserId(userId);
        tCart.setDtCreated(new Date());

        return tCartRepository.save(tCart);
    }

    private String effectivelyPlaceOrder(PlaceOrderRequestDto request, List<TCartItem> cartItems) {
        TOrder tOrder = new TOrder();
        tOrder.setEmOrderStatus(Constants.ORDER_STATUS.PENDING.name());
        tOrder.setDtOrderDate(new Date());
        tOrder.setStrOrderedBy(request.getOrdererId());
        tOrder.setBlComplete(false);
        tOrder.setStrBusinessId(request.getBusinessId());
        tOrder.setDbTotalAmount(request.getOrderTotal());
        tOrder.setIntItemCount(request.getItemCount());
        tOrder.setStrOrderId(UUID.randomUUID().toString());

        TOrder savedOrder = orderRepo.save(tOrder);

        for(TCartItem cartItem : cartItems) {
            cartItem.setStrOrderId(savedOrder.getStrOrderId());
            cartItem.setEmStatus(Constants.CART_ITEM_STATUS.PLACED.name());
        }
        tCartItemRepository.saveAll(cartItems);

        // Update product inventory stock
        try {
            Runnable r = () -> {
                JSONObject[] items = new JSONObject[cartItems.size()];
                for(int i=0; i<cartItems.size(); i++) {
                    items[i] = new JSONObject().put("item", cartItems.get(i).getLgItemId())
                            .put("qtyToUpdateBy", cartItems.get(i).getIntQty());
                }
                productService.updateItemsStock(items.toString());
            };
            new Thread(r).start();
            log.info("Order placed successfully !");

        } catch (Exception ex) {
            log.error("An error occured ", ex);
            return Utilities.errorMessageReturn("Unable to place order. Please try again later !");
        }
        return Utilities.dataReturn("Order placed successfully !");
    }

}
