package org.example.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    private static String generatorRandomString() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }
    public static Courier genericCourierRandom(){
        return new Courier(generatorRandomString(), generatorRandomString(), generatorRandomString());

    }
}
