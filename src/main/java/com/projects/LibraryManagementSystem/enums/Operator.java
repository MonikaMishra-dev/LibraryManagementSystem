package com.projects.LibraryManagementSystem.enums;

public enum Operator {
    EQUALS("="),
    CONTAINS("CONTAINS"),
    LIKE("LIKE"),
    IN("IN"),
    LESS_THAN("<"),
    LESS_THAN_EQUAL("<=");

    private String value;

    Operator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
