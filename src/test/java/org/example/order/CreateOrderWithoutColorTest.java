package org.example.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.example.constant.OrderList.TRACK;
import static org.example.order.OrderGenerator.genericOrderRandomWithoutColour;

public class CreateOrderWithoutColorTest {
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    @DisplayName("Check successfully post /api/v1/orders without color")
    @Description("Possible create order with color")
    public void orderCreatedWithoutColorPositive(){
        var order = genericOrderRandomWithoutColour();
        ValidatableResponse createResponse = client.createdOrder(order);
        int trackId = check.createdOrderSuccessfully(createResponse);
        client.cancelOrder(TRACK, trackId);
    }
}
