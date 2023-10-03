package org.example.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.example.courier.CourierGenerator.genericCourierRandom;


public class LoginCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    @Test
    @DisplayName("Check successfully post /api/v1/courier/login")
    @Description("Possible logged courier with correct data")
    public void courierLoggedPositive() {
        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        int courierId = check.loggedSuccessfully(loginResponse);
        assert courierId != 0;
        client.deleteCourier(courierId);
    }

    @Test
    @DisplayName("Check unsuccessfully post /api/v1/courier/login with non-existen data")
    @Description("Impossible  logged courier with non-existen data")
    public void courierLoggedNotExistLogPass(){
        var courier = genericCourierRandom();
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.loggedNotExistLogPassUnsuccessfully(loginResponse);
    }

    @Test
    @DisplayName("Check unsuccessfully post /api/v1/courier/login without required data")
    @Description("Impossible  logged courier without required data")
    public void courierLoggedNotEnoughData(){
        var courier = genericCourierRandom();
        courier.setLogin("");
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.loggedNotEnoughUnsuccessfully(loginResponse);
    }

    @Test
    @DisplayName("Check unsuccessfully post /api/v1/courier/login with incorrect password")
    @Description("Impossible  logged courier with incorrect password")
    public void courierLoggedErrorPass(){
        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        int courierId = check.loggedSuccessfully(loginResponse);
        courier.setPassword("000");
        var creds2 = Credentials.from(courier);
        ValidatableResponse loginErrorPassResponse = client.loginCourier(creds2);
        check.loggedNotExistLogPassUnsuccessfully(loginErrorPassResponse);
        client.deleteCourier(courierId);
    }
    @Test
    @DisplayName("Check unsuccessfully post /api/v1/courier/login without password")
    @Description("Impossible  logged courier without password")
    public void courierLoggedNullPass(){
        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        int courierId = check.loggedSuccessfully(loginResponse);
        courier.setPassword(null);
        var creds2 = Credentials.from(courier);
        ValidatableResponse loginNullPassResponse = client.loginCourier(creds2);
        check.loggedNullPassUnsuccessfully(loginNullPassResponse);
        client.deleteCourier(courierId);
    }
}
