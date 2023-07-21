package edu.poly.lab6.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// phân hành động

@RequiredArgsConstructor
public enum Permission {
    ADMIN_ALL_PERMISSIONS("ADMIN_ALL_PERMISSIONS"),
    USER_ALL_PERMISSIONS("USER_ALL_PERMISSIONS");

    @Getter
    final String value;

}
