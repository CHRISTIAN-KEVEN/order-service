package com.example.orderService.utilities;

public class Constants {
    public static int DEFAULT_PAGE_NUMBER = 0;
    public  static  int DEFAULT_PAGE_SIZE = 0;
    public static String erc = "erc";
    public static String PROCESS_SUCCESS = "1";

    public static String PROCESS_FAILED = "0";
    public static String data = "data";
    public static String msg = "msg";

    public static enum ORDER_STATUS {
        CREATED, PENDING, COMPLETED, CANCELLED
    }
    public static enum PaymentMethod {
        PAY_ON_DELIVERY, MOBILE_MONEY,
    }
}
