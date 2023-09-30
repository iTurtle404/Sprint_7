package org.example.constant;

public class URLForEndpoint {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";
    public static final String BASE_API = "/api/v1/";
    public   static final String COURIER_LOGIN_PATH = BASE_API + "courier/login";
    public   static final String COURIER_PATH = BASE_API + "courier";
    public   static final String COURIER_ORDERS_COUNT = "/api/v1/courier/:id/ordersCount";
    public   static final String ORDERS = BASE_API + "orders";
    public   static final String ORDERS_FINISH = BASE_API + "orders/finish/:id";
    public   static final String ORDERS_CANCEL = BASE_API + "orders/cancel";
    public   static final String ORDERS_TRACK = BASE_API + "orders/track";
    public   static final String ORDERS_ACCEPT = BASE_API +  "orders/accept/";
    public   static final String UTILS_PING = BASE_API + "ping";
    public   static final String UTILS_STATIONS_SEARCH = BASE_API + "stations/search";
}
