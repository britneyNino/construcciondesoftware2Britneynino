package application.model.entity;

import lombok.Getter;
import lombok.Setter;
import application.model.enums.SystemRole;
import application.model.enums.UserStatus;

@Getter
@Setter
public class User {

    private String userId;
    private String username;
    private String password;

    private SystemRole role;
    private UserStatus status;
}