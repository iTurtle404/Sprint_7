package org.example.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.constant.ErrorMessage;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CourierAssertions {
    @Step("Check of successfully logged and return courierId")
    public int loggedSuccessfully(ValidatableResponse loginResponse) {
        int id = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id");
        return id;
    }
    @Step("Check of successfully created new Courier from random")
    public void createdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .body("ok",is(true));
    }


    @Step("Check of unsuccessfully  for created Twin Courier")
    public void createdTwinUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.EXIST_LOGIN))
                .and()
                .statusCode(HttpURLConnection.HTTP_CONFLICT);

    }
    @Step("Check of unsuccessfully  created Courier with empty data")
    public void createdEmptyUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_CREATED))
                .and()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST);

    }
    @Step("Check of unsuccessfully  created courier with not enough data")
    public void createdBadUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_CREATED))
                .and()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST);

    }
    @Step("Check of successfully deleted Courier")
    public void deletedSuccessfulle(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK);
    }
    @Step("Check of unsuccessfully deleted Courier by not found")
    public void deletedNotFoundIdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.COURIER_NOT_FOUND))
                .and()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND);
    }
    @Step("Check of unsuccessfully deleted Courier without id")
    public void deletedWithoutIdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.COURIER_WITHOUT_ID))
                .and()
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }

    @Step("Check of unsuccessfully logged Courier with non-existen data")
    public void loggedNotExistLogPassUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.LOGGED_NOT_EXIST_LOG_PASS))
                .and()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND);
    }
    @Step("Check of unsuccessfully logged Courier with  not enough data")
    public void loggedNotEnoughUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.LOGGED_NOT_ENOUGH_DATA))
                .and()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST);
    }
    @Step("Check of unsuccessfully logged Courier with null password")
    public void loggedNullPassUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .and()
                .statusCode(HttpURLConnection.HTTP_GATEWAY_TIMEOUT);
    }
    @Step("Check of unsuccessfully created Courier with already exist login")
    public void createdExistLoginUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.COURIER_EXIST_LOGIN))
                .and()
                .statusCode(HttpURLConnection.HTTP_CONFLICT);
    }
}
