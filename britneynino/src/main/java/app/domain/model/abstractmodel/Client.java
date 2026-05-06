package application.model.abstractmodel;

import lombok.Getter;
import lombok.Setter;
import application.model.entity.User;

@Getter
@Setter
public abstract class Client {

    protected String identification;
    protected String email;
    protected String phone;
    protected String address;

    protected User user;
