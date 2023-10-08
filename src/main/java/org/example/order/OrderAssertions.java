package org.example.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.constant.ErrorMessage;
import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.*;

public class OrderAssertions {

    @Step("Check of successfully created Order and return trackId")
    public int createdOrderSuccessfully(ValidatableResponse createdResponse) {
        int track = createdResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");
        return track;
    }

    @Step("Check of successfully get Order with correct data")
    public void getOrderSuccessfully(ValidatableResponse response) {
       response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("order",notNullValue());

    }

    @Step("Check of successfully get List Order without Param")
    public void getOrderListWithoutParamSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders",notNullValue());
    }

    @Step("Check of successfully get List Order with courierId")
    public void getOrderListWithCourierIdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders",notNullValue());
    }

    @Step("Check of successfully get List Order with near Station")
    public void getOrderListWithStationSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders",notNullValue());
    }

    @Step("Check of successfully get List Orders with limit")
    public void getOrderListWithLimitSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders",notNullValue());
    }

    @Step("Check of successfully get List Order with limit and near station")
    public void getOrderListWithLimitStationSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders",notNullValue());
    }

    @Step("Check of unsuccessfully get Order by non-existen number")
    public void getOrderNotExistUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.ORDER_NOT_FOUND))
                .and()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND);
    }

    @Step("Check of successfully cancel order")
    public void cancelOrderSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("ok",is(true));
    }

    @Step("Check of unsuccessfully cancel order without ID")
    public void cancelOrderWithoutIdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message",equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOUND));
    }

    @Step("Check of unsuccessfully cancel order with non-exist ID")
    public void cancelOrderWithNotExistIdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body("message",equalTo(ErrorMessage.ORDER_NOT_FOUND));

    }
}
