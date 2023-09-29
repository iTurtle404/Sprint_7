package org.example.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.example.courier.CourierGenerator.genericCourierRandom;


public class LoginCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;


   /*@After
    public void deleteCourier() {
        ValidatableResponse delete = client.deleteCourier(courierId);
        check.deletedSuccessfulle(delete);}
    */

    @Test
    public void courierLoggedPositive() {

        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        assert courierId != 0;
        client.deleteCourier(courierId);
    }

    @Test
    public void courierLoggedNotExistLogPass(){
        var courier = genericCourierRandom();
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.loggedNotExistLogPassUnsuccessfully(loginResponse);
    }

    @Test
    public void courierLoggedNotEnoughData(){
        var courier = genericCourierRandom();
        courier.setLogin("");
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.loggedNotEnoughUnsuccessfully(loginResponse);
    }

    @Test
    public void courierLoggedErrorPass(){
        var courier = genericCourierRandom();
        client.createCourier(courier);
        courier.setPassword("000");
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.loggedNotExistLogPassUnsuccessfully(loginResponse);
    }
    @Test
    public void courierLoggedNullPass(){
        var courier = genericCourierRandom();
        client.createCourier(courier);
        courier.setPassword(null);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.loggedNullPassUnsuccessfully(loginResponse);
    }
}
