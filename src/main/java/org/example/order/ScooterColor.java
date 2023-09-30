package org.example.order;

public enum ScooterColor {
    NO_COLOR(new String[]{}),
    BLACK(new String[]{"BLACK"}),
    GRAY(new String[]{"GRAY"}),
    BOTH_COLOR(new String[]{"BLACK", "GRAY"});

    private String[] value;

    ScooterColor(String[] value) {
        this.value = value;
    }

    public String[] getValue() {
        return value;
    }

}
