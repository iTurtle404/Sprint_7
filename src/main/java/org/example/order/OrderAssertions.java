package org.example.order;

import io.restassured.response.ValidatableResponse;

import java.awt.*;
import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {

    public int createdOrderSuccessfully(ValidatableResponse createdResponse) {
        int track = createdResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");
        return track;

    }

    public void acceptedOrderSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("ok",is(true));
    }

    public void getOrderSuccessfully(ValidatableResponse response) {
       response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("order",notNullValue());

    }

    public void getOrderListWithoutParamSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders",notNullValue());
    }

    public void getOrderListWithCourierIdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders",notNullValue());
    }

    public void getOrderListWithStationSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders",notNullValue());
    }

    public void getOrderListWithLimitSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders",notNullValue());
    }

    public void getOrderListWithLimitStationSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders",notNullValue());
    }
}
