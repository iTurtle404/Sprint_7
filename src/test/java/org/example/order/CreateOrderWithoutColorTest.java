package org.example.order;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.example.order.OrderGenerator.genericOrderRandomWithoutColour;

public class CreateOrderWithoutColorTest {
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    public void orderCreatedWithoutColorPositive(){
        var order = genericOrderRandomWithoutColour();
        ValidatableResponse createResponse = client.createdOrder(order);
        check.createdOrderSuccessfully(createResponse);
    }

}
