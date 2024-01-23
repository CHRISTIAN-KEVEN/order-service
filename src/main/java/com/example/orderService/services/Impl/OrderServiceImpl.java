package com.example.orderService.services.Impl;

import com.example.orderService.dtos.requestDtos.PlaceOrderRequestDto;
import com.example.orderService.feigns.ProductService;
import com.example.orderService.models.TOrder;
import com.example.orderService.repositories.OrderRepository;
import com.example.orderService.services.OrderService;
import com.example.orderService.utilities.Constants;
import com.example.orderService.utilities.Utilities;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepo;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepo, ProductService productService) {
        this.orderRepo = orderRepo;
        this.productService = productService;
    }

    public ResponseEntity<String> addToCart(String request) {
        JSONObject requestBody = new JSONObject(request);
        return new ResponseEntity<>("Coming soon...", HttpStatus.OK);
    }

    @Override
    public String placeOrder(PlaceOrderRequestDto request) {

        // Check that stock is available or product is still valid
        // Save order with status created
        // Get payment
        // Update order status to paid
        // Update stock
        try {
            log.info("Getting product details");
            JSONObject resp = new JSONObject(productService.getProductDetails(request.getItemId()));
            if(resp == null || !Constants.PROCESS_SUCCESS.equals(resp.optString("erc", ""))) {
                log.info("Unable to get product details");
                return Utilities.errorMessageReturn("Unable to place order. Please try again later !");
            }
            JSONObject item = resp.getJSONObject("data");
            if(item.getInt("quant"))
        } catch (Exception ex) {
            log.error("An error occured ", ex);
            return Utilities.errorMessageReturn("Unable to place order. Please try again later !");
        }
        log.info("Placing order request {}", request);
        TOrder tOrder = new TOrder();
        tOrder.setIntQty(request.getQuantity());
        tOrder.setDtOrderDate(Instant.now());
        tOrder.setStrStatus(Constants.ORDER_STATUS.CREATED.name());
        tOrder.setDbTotalAmount(request.getOrderTotal());
        tOrder.setLgItemId(request.getItemId());

        orderRepo.save(tOrder);
        log.info("Order placed successfully !");
        return Utilities.successMessageReturn("Order placed successfully !");
    }

}
