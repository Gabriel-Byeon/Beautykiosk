package com.beauty.Beautykiosk.User;

import lombok.Getter;


@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
