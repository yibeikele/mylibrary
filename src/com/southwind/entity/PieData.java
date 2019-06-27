package com.southwind.entity;

public class PieData {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public PieData(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
