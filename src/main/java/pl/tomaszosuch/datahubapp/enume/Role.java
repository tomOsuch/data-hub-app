package pl.tomaszosuch.datahubapp.enume;

import lombok.Getter;

@Getter
public enum Role {

    CUSTOMER("customer", "user"),
    CONTENT_MANAGER("manager", "admin");

    private final String roleName;
    private final String password;

    Role(String roleName, String password) {
        this.roleName = roleName;
        this.password = password;
    }
}
