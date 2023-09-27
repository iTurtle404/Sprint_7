package org.example.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.example.courier.CourierGenerator.genericCourierRandom;

public class CourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;

    @After
    public void deleteCourier(){
        ValidatableResponse delete = client.deleteCourier(courierId);
        check.deletedSuccessfulle(delete);
    }

    @Test

    public void courier() {

        var  courier = genericCourierRandom();
        ValidatableResponse createdResponse = client.createCourier(courier);
        check.createdSuccessfully(createdResponse);


        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        assert courierId != 0;


    }


}
