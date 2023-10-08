package org.example.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.CourierAssertions;
import org.example.courier.CourierClient;
import org.example.courier.Credentials;
import org.junit.Test;

import static org.example.constant.OrderList.TRACK;
import static org.example.courier.CourierGenerator.genericCourierRandom;
import static org.example.order.OrderGenerator.genericOrderRandomWithoutColour;

public class CancelOrderTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private final OrderClient clientOrd = new OrderClient();
    private final OrderAssertions checkOrd = new OrderAssertions();

    @Test
    @DisplayName("Check successfully put /api/v1/orders/cancel with correct data")
    @Description("Possible cancel order with correct data")
    public void cancelOrderPositive() {
        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        int courierId = check.loggedSuccessfully(loginResponse);
        var order = genericOrderRandomWithoutColour();
        ValidatableResponse createResponse = clientOrd.createdOrder(order);
        int trackId = checkOrd.createdOrderSuccessfully(createResponse);
        ValidatableResponse cancelResponse = clientOrd.cancelOrder(TRACK, trackId);
        checkOrd.cancelOrderSuccessfully(cancelResponse);
        client.deleteCourier(courierId);
    }
    @Test
    @DisplayName("Check unsuccessfully put /api/v1/orders/cancel without track id")
    @Description("Impossible cancel order without track id")
    public void cancelOrderWithoutId() {
        ValidatableResponse cancelOrderWithoutIdResponse = clientOrd.cancelOrderWithoutOrder();
        checkOrd.cancelOrderWithoutIdSuccessfully(cancelOrderWithoutIdResponse);
    }

    @Test
    @DisplayName("Check unsuccessfully put /api/v1/orders/cancel with non-existen id")
    @Description("Impossible cancel order with non-existen id")
    public void cancelOrderWithNotExistId() {
        int notExist = 0;
        ValidatableResponse cancelOrderWithNotExistIdResponse = clientOrd.cancelOrderWithNotExistOrder(TRACK, notExist);
        checkOrd.cancelOrderWithNotExistIdSuccessfully(cancelOrderWithNotExistIdResponse);
    }
}
