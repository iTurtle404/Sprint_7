package org.example.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class BadCourierGenerator {
    private static String generatorRandomString() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }
    public static BadCourier genericBadCourierRandom(){
        return new BadCourier(generatorRandomString(), generatorRandomString());

    }
}
