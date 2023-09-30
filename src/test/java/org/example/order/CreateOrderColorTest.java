package org.example.order;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderColorTest extends OrderGenerator {
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();
        private String testName;
        private String[] color;
        public CreateOrderColorTest(String testName, String[] color) {
            this.testName = testName;
            this.color = color;
        }

        @Parameterized.Parameters(name = "Color field value: {0}")
        public static Object[][] getColorValue() {
            Object[][] objects = {
                    { ScooterColor.NO_COLOR.name(), ScooterColor.NO_COLOR.getValue()},
                    { ScooterColor.BLACK.name(), ScooterColor.BLACK.getValue() },
                    { ScooterColor.GRAY.name(), ScooterColor.GRAY.getValue() },
                    { ScooterColor.BOTH_COLOR.name(), ScooterColor.BOTH_COLOR.getValue()},
            };
            return objects;
        }
    @Before
    public void setUp() {
        genericOrderRandomWithColour(color);
    }

    @Test
    public void orderCreatedWithColorPositive() {
        var order = genericOrderRandomWithColour(color);
        ValidatableResponse createResponse = client.createdOrder(order);
        check.createdOrderSuccessfully(createResponse);
    }

}

