package org.example.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.Client;

import java.util.Map;

import static org.example.constant.OrderList.COURIER_ID;
import static org.example.constant.OrderList.NEAREST_STATION;
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

    public ValidatableResponse acceptedOrder(int courierId, int trackId) {
        return spec()
                .when()
                .put(ORDERS_ACCEPT+trackId +"?courierId="+courierId)
                .then().log().all();
    };

    public ValidatableResponse getOrderListWithoutParam() {
        return spec()
                .when()
                .get(ORDERS)
                .then().log().all();
    }

    public ValidatableResponse getOrderListWithCourierId(String id, String idValue) {
        return spec()
                .when()
                .queryParam(id, idValue)
                .get(ORDERS)
                .then().log().all();
    }
    public ValidatableResponse getOrderListWithStation(String id, String idValue, String nearestStation, String nearStationValue) {
        return spec()
                .when()
                .queryParam(id, idValue)
                .queryParam(nearestStation, nearStationValue)
                .get(ORDERS)
                .then().log().all();
    }

    public ValidatableResponse getOrderListWithLimit(String id, String idValue, String station, String stationValue) {
        return spec()
                .when()
                .queryParam(id, idValue)
                .queryParam(station,stationValue)
                .get(ORDERS)
                .then().log().all();
    }

    public ValidatableResponse getOrderListWithLimitStation(String limit, String limitValue, String page, String pageValue, String nearestStation, String nearStationValue) {
        return spec()
                .when()
                .queryParam(limit, limitValue)
                .queryParam(page,pageValue)
                .queryParam(nearestStation,nearStationValue )
                .get(ORDERS)
                .then().log().all();
    }
}
