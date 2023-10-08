package org.example.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.example.constant.OrderList.TRACK;

@DisplayName("Check successfully post /api/v1/orders with color")
@RunWith(Parameterized.class)
public class CreateOrderColorTest extends OrderGenerator {
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();
    private final String[] color;
        public CreateOrderColorTest(String testName, String[] color) {
            this.color = color;
        }

        @Parameterized.Parameters(name = "Color field value: {0}")
        public static Object[][] getColorValue() {
            return new Object[][]{
                    { ScooterColor.NO_COLOR.name(), ScooterColor.NO_COLOR.getValue()},
                    { ScooterColor.BLACK.name(), ScooterColor.BLACK.getValue() },
                    { ScooterColor.GRAY.name(), ScooterColor.GRAY.getValue() },
                    { ScooterColor.BOTH_COLOR.name(), ScooterColor.BOTH_COLOR.getValue()},
            };
        }
    @Before
    public void setUp() {
        genericOrderRandomWithColour(color);
    }

    @Test
    @Description("Possible create order with color")
    public void orderCreatedWithColorPositive() {
        var order = genericOrderRandomWithColour(color);
        ValidatableResponse createResponse = client.createdOrder(order);
        int trackId= check.createdOrderSuccessfully(createResponse);
        client.cancelOrder(TRACK, trackId);
    }
}

