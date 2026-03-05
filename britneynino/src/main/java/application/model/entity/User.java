package application.model.entity;

import application.model.enums.SystemRole;
import application.model.enums.UserStatus;

public class User {

    private int userId;
    private String fullName;
    private String identificationNumber;
    private String email;
    private String phone;
    private String address;
    private SystemRole role;
    private UserStatus status;

}