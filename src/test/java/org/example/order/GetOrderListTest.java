package org.example.order;

import io.restassured.response.ValidatableResponse;
import org.example.courier.CourierAssertions;
import org.example.courier.CourierClient;
import org.example.courier.Credentials;
import org.junit.Test;

import static org.example.constant.OrderList.*;
import static org.example.courier.CourierGenerator.genericCourierRandom;

import static org.example.order.OrderGenerator.genericOrderRandomWithoutColour;

public class GetOrderListTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private final OrderClient clientOrd = new OrderClient();
    private final OrderAssertions checkOrd = new OrderAssertions();
    private int courierId;
    private int orderId;

   /* @Test
    public void getOrderListWithoutParam() {
        ValidatableResponse getOrderListWithoutParamResponse = clientOrd.getOrderListWithoutParam();
        checkOrd.getOrderListWithoutParamSuccessfully(getOrderListWithoutParamResponse);
    } */
    private static final String NEAR_TWO_STATION_VALUE ="[\"" + NEAR_STATION_FIRST + "\", \"" + NEAR_STATION_SECOND+ "\"]";
    private static final String NEAR_ONE_STATION_VALUE = "110";
    private static final String LIMIT_VALUE = "10";
    private static final String PAGE_VALUE = "0";
    @Test
    public void getOrderListWithCourierId() {
        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        var order = genericOrderRandomWithoutColour();
        ValidatableResponse createResponse = clientOrd.createdOrder(order);
        orderId = checkOrd.createdOrderSuccessfully(createResponse);
        ValidatableResponse getOrderListWithCourierIdResponse = clientOrd.getOrderListWithCourierId(COURIER_ID,String.valueOf(courierId));
        checkOrd.getOrderListWithCourierIdSuccessfully(getOrderListWithCourierIdResponse);
        client.deleteCourier(courierId);
    }

    @Test
    public void getOrderListWithCouriedIdStation() {

        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        var order = genericOrderRandomWithoutColour();
        ValidatableResponse createResponse = clientOrd.createdOrder(order);
        orderId = checkOrd.createdOrderSuccessfully(createResponse);
        ValidatableResponse getOrderListWithStationResponse = clientOrd.getOrderListWithStation(COURIER_ID,String.valueOf(courierId), NEAREST_STATION, NEAR_TWO_STATION_VALUE);
        checkOrd.getOrderListWithStationSuccessfully(getOrderListWithStationResponse);
        client.deleteCourier(courierId);
    }
    @Test
    public void getOrderListWithLimit() {

        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        var order = genericOrderRandomWithoutColour();
        ValidatableResponse createResponse = clientOrd.createdOrder(order);
        orderId = checkOrd.createdOrderSuccessfully(createResponse);
        ValidatableResponse getOrderListWithLimit = clientOrd.getOrderListWithLimit(LIMIT, LIMIT_VALUE, PAGE,PAGE_VALUE);
        checkOrd.getOrderListWithLimitSuccessfully(getOrderListWithLimit);
        client.deleteCourier(courierId);
    }

    @Test
    public void getOrderListWithLimitStation() {

        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        var order = genericOrderRandomWithoutColour();
        ValidatableResponse createResponse = clientOrd.createdOrder(order);
        orderId = checkOrd.createdOrderSuccessfully(createResponse);
        ValidatableResponse getOrderListWithLimitStation = clientOrd.getOrderListWithLimitStation(LIMIT, LIMIT_VALUE, PAGE,PAGE_VALUE, NEAREST_STATION,NEAR_ONE_STATION_VALUE );
        checkOrd.getOrderListWithLimitStationSuccessfully(getOrderListWithLimitStation);
        client.deleteCourier(courierId);
    }
}
