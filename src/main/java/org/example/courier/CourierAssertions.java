package org.example.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.constant.ErrorMessage;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CourierAssertions {
    @Step("Check of successfully logged")
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


    @Step("Error for created Tmin Courier")
    public void createdTwinUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.EXIST_LOGIN))
                .and()
                .statusCode(HttpURLConnection.HTTP_CONFLICT);

    }

    public void createdEmptyUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA))
                .and()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST);

    }

    public void createdBadUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA))
                .and()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST);

    }
    @Step("Check of successfully deleted Courier")
    public void deletedSuccessfulle(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK);
    }

    public void deletedNotFoundIdSuccessfulle(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.COURIER_NOT_FOUND))
                .and()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND);
    }

    public void deletedWithoutIdSuccessfulle(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.COURIER_WITHOUT_ID))
                .and()
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }


    public void loggedNotExistLogPassUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.LOGGED_NOT_EXIST_LOG_PASS))
                .and()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND);
    }

    public void loggedNotEnoughUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("message", equalTo(ErrorMessage.LOGGED_NOT_ENOUGH_DATA))
                .and()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    public void loggedNullPassUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .and()
                .statusCode(HttpURLConnection.HTTP_GATEWAY_TIMEOUT);
    }
}
