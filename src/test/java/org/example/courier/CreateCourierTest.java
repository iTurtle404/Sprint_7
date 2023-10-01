package org.example.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import static org.example.courier.BadCourierGenerator.genericBadCourierRandom;
import static org.example.courier.CourierGenerator.genericCourierRandom;

public class CreateCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    @Test
    @DisplayName("Check successfully post /api/v1/courier")
    @Description("Possible created courier with correct data")
    public void courierCreatedPositive() {
        var courier = genericCourierRandom();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        int courierId = check.loggedSuccessfully(loginResponse);
        client.deleteCourier(courierId);

    }
    @Test
    @DisplayName("Check unsuccessfully post /api/v1/courier by twin courier")
    @Description("Impossible created twin courier")
    public void courierTwinsCreated () {
        var courier = genericCourierRandom();
        client.createCourier(courier);
        ValidatableResponse createTwinRespone  = client.createCourier(courier);
        check.createdTwinUnsuccessfully(createTwinRespone);
    }
    @Test
    @DisplayName("Check unsuccessfully post /api/v1/courier with exist login")
    @Description("Impossible created courier with exist login")
    public void courierExistLoginCreated () {
        var courier = genericCourierRandom();
        client.createCourier(courier);
        courier.setPassword("000");
        courier.setFirstName("lll");
        var courier2 = courier;
        ValidatableResponse createExistLoginRespone  = client.createCourier(courier2);
        check.createdExistLoginUnsuccessfully(createExistLoginRespone);
    }

    @Test
    @DisplayName("Check unsuccessfully post /api/v1/courier with empty login")
    @Description("Impossible created courier with empty login")
    public void courierEmptyCreated(){
        var courier = genericCourierRandom();
        courier.setLogin("");
        ValidatableResponse createEmptyResponse = client.createCourier(courier);
        check.createdEmptyUnsuccessfully(createEmptyResponse);
    }
    @Test
    @DisplayName("Check unsuccessfully post /api/v1/courier without required data")
    @Description("Impossible created courier without required data")
    public void courierBadCreated(){
        var badCourier = genericBadCourierRandom();
        ValidatableResponse createBadResponse = client.createBadCourier(badCourier);
        check.createdBadUnsuccessfully(createBadResponse);
    }
}
