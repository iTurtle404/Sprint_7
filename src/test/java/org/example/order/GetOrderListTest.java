package org.example.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.CourierAssertions;
import org.example.courier.CourierClient;
import org.example.courier.Credentials;
import org.junit.Test;

import static org.example.constant.OrderList.*;
import static org.example.courier.CourierGenerator.genericCourierRandom;

public class GetOrderListTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private final OrderClient clientOrd = new OrderClient();
    private final OrderAssertions checkOrd = new OrderAssertions();
    private int courierId;

    @Test
    @DisplayName("Check successfully get /api/v1/orders all orders")
    @Description("Possible get info about all orders without param")
    public void getOrderListWithoutParam() {
        ValidatableResponse getOrderListWithoutParamResponse = clientOrd.getOrderListWithoutParam();
        checkOrd.getOrderListWithoutParamSuccessfully(getOrderListWithoutParamResponse);
    }

    @Test
    @DisplayName("Check successfully get /api/v1/orders by courier id")
    @Description("Possible get info about orders by courier id")
    public void getOrderListWithCourierId() {
        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        ValidatableResponse getOrderListWithCourierIdResponse = clientOrd.getOrderListWithCourierId(COURIER_ID,String.valueOf(courierId));
        checkOrd.getOrderListWithCourierIdSuccessfully(getOrderListWithCourierIdResponse);
        client.deleteCourier(courierId);
    }

    @Test
    @DisplayName("Check successfully get /api/v1/orders by courier id & near station")
    @Description("Possible get info about orders by courier id & near station")
    public void getOrderListWithCouriedIdStation() {
        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        ValidatableResponse getOrderListWithStationResponse = clientOrd.getOrderListWithStation(COURIER_ID,String.valueOf(courierId), NEAREST_STATION, NEAR_TWO_STATION_VALUE);
        checkOrd.getOrderListWithStationSuccessfully(getOrderListWithStationResponse);
        client.deleteCourier(courierId);
    }

    @Test
    @DisplayName("Check successfully get /api/v1/orders  limit available orders")
    @Description("Possible get info about limit available orders")
    public void getOrderListWithLimit() {
        ValidatableResponse getOrderListWithLimit = clientOrd.getOrderListWithLimit(LIMIT, LIMIT_VALUE, PAGE,PAGE_VALUE);
        checkOrd.getOrderListWithLimitSuccessfully(getOrderListWithLimit);
    }

    @Test
    @DisplayName("Check successfully get /api/v1/orders  limit available orders & near station")
    @Description("Possible get info about limit available orders & near station")
    public void getOrderListWithLimitStation() {
        ValidatableResponse getOrderListWithLimitStation = clientOrd.getOrderListWithLimitStation(LIMIT, LIMIT_VALUE, PAGE,PAGE_VALUE, NEAREST_STATION,NEAR_ONE_STATION_VALUE );
        checkOrd.getOrderListWithLimitStationSuccessfully(getOrderListWithLimitStation);
    }
}
