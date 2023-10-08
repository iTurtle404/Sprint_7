package org.example.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.Client;

import java.util.Map;

import static org.example.constant.URLForEndpoint.*;

public class OrderClient extends Client {
    @Step("Create Order")
    public ValidatableResponse createdOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDERS)
                .then().log().all();
    }

    @Step("Create Order")
    public ValidatableResponse getOrder(int trackId) {
        Order order = new Order();
        return spec()
                .body(order)
                .when()
                .get(ORDERS_TRACK+"?t="+ trackId)
                .then().log().all();
    }
    @Step("Get Order List without param")
    public ValidatableResponse getOrderListWithoutParam() {
        return spec()
                .when()
                .get(ORDERS)
                .then().log().all();
    }

    @Step("Get Order List with courier id")
    public ValidatableResponse getOrderListWithCourierId(String id, String idValue) {
        return spec()
                .when()
                .queryParam(id, idValue)
                .get(ORDERS)
                .then().log().all();
    }
    @Step("Get Order List with near station")
    public ValidatableResponse getOrderListWithStation(String id, String idValue, String nearestStation, String nearStationValue) {
        return spec()
                .when()
                .queryParam(id, idValue)
                .queryParam(nearestStation, nearStationValue)
                .get(ORDERS)
                .then().log().all();
    }

    @Step("Get Order List with limit")
    public ValidatableResponse getOrderListWithLimit(String id, String idValue, String station, String stationValue) {
        return spec()
                .when()
                .queryParam(id, idValue)
                .queryParam(station,stationValue)
                .get(ORDERS)
                .then().log().all();
    }

    @Step("Get Order List with limit and near station")
    public ValidatableResponse getOrderListWithLimitStation(String limit, String limitValue, String page, String pageValue, String nearestStation, String nearStationValue) {
        return spec()
                .when()
                .queryParam(limit, limitValue)
                .queryParam(page,pageValue)
                .queryParam(nearestStation,nearStationValue )
                .get(ORDERS)
                .then().log().all();
    }

    @Step("Cancel Order with correct data")
    public ValidatableResponse cancelOrder(String trackId, int valueTrackId) {
        return spec()
                .when()
                .queryParam(trackId,valueTrackId)
                .put(ORDERS_CANCEL)
                .then().log().all();
    }

    @Step("Cancel Order without ID")
    public ValidatableResponse cancelOrderWithoutOrder() {
        return spec()
                .when()
                .put(ORDERS_CANCEL)
                .then().log().all();
    }

    @Step("Cancel Order with non-exist ID")
    public ValidatableResponse cancelOrderWithNotExistOrder(String trackId, int valueTrackId) {
        return spec()
                .when()
                .queryParam(trackId,valueTrackId)
                .put(ORDERS_CANCEL)
                .then().log().all();
    }
}
