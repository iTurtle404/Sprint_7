package org.example.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.example.courier.CourierGenerator.genericCourierRandom;

public class DeleteCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;



    @Test
    public void courierDeletePositive() {

        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        ValidatableResponse delete = client.deleteCourier(courierId);
        check.deletedSuccessfulle(delete);
    }

    @Test
    public  void  couirierDeleteNotFoundId(){
        var courier = genericCourierRandom();
        client.createCourier(courier);
        ValidatableResponse delete = client.deleteCourier(courierId);
        check.deletedNotFoundIdSuccessfulle(delete);
    }

    @Test
    public  void  couirierDeleteWithoutId(){
        var courier = genericCourierRandom();
        client.createCourier(courier);
        ValidatableResponse delete = client.deleteCourierWithoutId();
        check.deletedWithoutIdSuccessfulle(delete);
    }

}
