package org.example.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;

import static org.example.constant.URLForEndpoint.*;

public class CourierClient extends org.example.Client {
    @Step("Logged Courier")
    public ValidatableResponse loginCourier(Credentials cred) {
        return spec()
                .body(cred)
                .when()
                .post(COURIER_LOGIN_PATH)
                .then().log().all();

    }
    @Step("Created new Courier from random")
    public  ValidatableResponse createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Created new Courier from random")
    public  ValidatableResponse createBadCourier(BadCourier badCourier) {
        return spec()
                .body(badCourier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Deleted Courier with ID")
    public ValidatableResponse deleteCourier(int courierId) {
        return spec()
                .body(Map.of("id",String.valueOf(courierId)))
                .when()
                .delete(COURIER_PATH+"/"+courierId)
                .then().log().all();
    }

    @Step("Deleted Courier with ID")
    public ValidatableResponse deleteCourierWithoutId() {
        return spec()
                .when()
                .delete(COURIER_PATH+"/:id")
                .then().log().all();
    }
}
