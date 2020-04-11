package com.aromero.videoserver.services.user.model;

public enum RolesEnum {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;

    private RolesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
