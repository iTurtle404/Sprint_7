package org.example.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.example.courier.BadCourierGenerator.genericBadCourierRandom;
import static org.example.courier.CourierGenerator.genericCourierRandom;

public class CreateCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;

    @Test
    public void courierCreatedLoggedPositive () {

        var courier = genericCourierRandom();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        assert courierId != 0;
        client.deleteCourier(courierId);

    }
    @Test
    public void courierTwinsCreated () {
        var courier = genericCourierRandom();
        client.createCourier(courier);
        ValidatableResponse createTwinRespone  = client.createCourier(courier);
        check.createdTwinUnsuccessfully(createTwinRespone);
    }

    @Test
    public void courierEmptyCreated(){
        var courier = genericCourierRandom();
        courier.setLogin("");
        ValidatableResponse createEmptyResponse = client.createCourier(courier);
        check.createdEmptyUnsuccessfully(createEmptyResponse);
    }
    @Test
    public void courierBadCreated(){
        var badCourier = genericBadCourierRandom();
        ValidatableResponse createBadResponse = client.createBadCourier(badCourier);
        check.createdBadUnsuccessfully(createBadResponse);
    }
}
