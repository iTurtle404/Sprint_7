package org.example.courier;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
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



    @Step("Deleted Courier")
    public ValidatableResponse deleteCourier(int courierId) {
        return spec()
                .body(Map.of("id",String.valueOf(courierId)))
                .when()
                .delete(COURIER_PATH+"/"+courierId)
                .then().log().all();
    }
}
