package org.example.order;

import io.restassured.response.ValidatableResponse;
import org.example.courier.CourierAssertions;
import org.example.courier.CourierClient;
import org.example.courier.Credentials;
import org.junit.Test;

import static org.example.courier.CourierGenerator.genericCourierRandom;
import static org.example.order.OrderGenerator.genericOrderRandomWithoutColour;

public class GetOrdeByNumrTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private final OrderClient clientOrd = new OrderClient();
    private final OrderAssertions checkOrd = new OrderAssertions();
    private int courierId;
    private int orderId;

    @Test
    public void getOrderByNum() {
        var courier = genericCourierRandom();
        client.createCourier(courier);
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);
        var order = genericOrderRandomWithoutColour();
        ValidatableResponse createResponse = clientOrd.createdOrder(order);
        orderId = checkOrd.createdOrderSuccessfully(createResponse);
        ValidatableResponse getOrderResponse = clientOrd.getOrder(orderId);
        checkOrd.getOrderSuccessfully(getOrderResponse);
        client.deleteCourier(courierId);
    }
}
