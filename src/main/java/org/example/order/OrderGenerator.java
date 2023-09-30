package org.example.order;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class OrderGenerator {
    private static Random random = new Random();
    private static final String phone  = "+7 800 355 35 35";
    private static final String deliveryDate = "2020-06-06";
    //private static final String color;

    private static String generatorRandomString() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }
    private static int generatorRandomNumber(){return random.nextInt(50);}
    public static Order genericOrderRandomWithoutColour() {
        return new Order(
                generatorRandomString(),
                generatorRandomString(),
                generatorRandomString(),
                generatorRandomNumber(),
                phone,
                generatorRandomNumber(),
                deliveryDate,
                generatorRandomString()
        );
    }
    public static Order genericOrderRandomWithColour(String [] color) {
        return new Order(
                generatorRandomString(),
                generatorRandomString(),
                generatorRandomString(),
                generatorRandomNumber(),
                phone,
                generatorRandomNumber(),
                deliveryDate,
                generatorRandomString(),
                color
        );
    }



}