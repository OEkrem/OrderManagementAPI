package com.oekrem.SpringMVCBackEnd.models.enums;

public enum Role {
    ROLE_ADMIN ("ROLE_ADMIN"),
    ROLE_USER ("ROLE_USER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
