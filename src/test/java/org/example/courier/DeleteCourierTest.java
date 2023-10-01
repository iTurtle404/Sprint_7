package org.example.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.example.courier.CourierGenerator.genericCourierRandom;

public class DeleteCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;

    @Test
    @DisplayName("Check successfully delete /api/v1/courier/:id with correct data")
    @Description("Possible delete courier with correct data")
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
    @DisplayName("Check unsuccessfully delete /api/v1/courier/:id with non-existen id")
    @Description("Impossible  delete courier with non-existen id")
    public  void courierDeleteNotFoundId(){
        ValidatableResponse delete = client.deleteCourier(courierId);
        check.deletedNotFoundIdSuccessfully(delete);
    }

    @Test
    @DisplayName("Check unsuccessfully delete /api/v1/courier/:id without id")
    @Description("Impossible  delete courier without id")
    public  void courierDeleteWithoutId(){
        ValidatableResponse delete = client.deleteCourierWithoutId();
        check.deletedWithoutIdSuccessfully(delete);
    }
}
