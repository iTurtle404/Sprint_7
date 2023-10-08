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

public class GetOrdeByNumrTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private final OrderClient clientOrd = new OrderClient();
    private final OrderAssertions checkOrd = new OrderAssertions();
    private int trackId;

    @Test
    @DisplayName("Check successfully get /api/v1/orders/track")
    @Description("Possible get info by number order")
    public void getOrderByNumPositive() {
        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        int courierId = check.loggedSuccessfully(loginResponse);
        var order = genericOrderRandomWithoutColour();
        ValidatableResponse createResponse = clientOrd.createdOrder(order);
        trackId = checkOrd.createdOrderSuccessfully(createResponse);
        ValidatableResponse getOrderResponse = clientOrd.getOrder(trackId);
        checkOrd.getOrderSuccessfully(getOrderResponse);
        client.deleteCourier(courierId);
        clientOrd.cancelOrder(TRACK, trackId);
    }
    @Test
    @DisplayName("Check unsuccessfully get /api/v1/orders/track by non-existen number")
    @Description("Impossible get info by non-existen number")
    public void getOrderByNumNotExistNum() {
        ValidatableResponse getOrderNotExistResponse = clientOrd.getOrder(trackId);
        checkOrd.getOrderNotExistUnsuccessfully(getOrderNotExistResponse);
    }
}
