package pl.tomaszosuch.datahubapp.enume;

import lombok.Getter;

@Getter
public enum Role {

    CUSTOMER("customer", "customer"),
    CONTENT_MANAGER("manager", "manager");

    private final String roleName;
    private final String password;

    Role(String roleName, String password) {
        this.roleName = roleName;
        this.password = password;
    }
}
