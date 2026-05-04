package domain.model.abstractmodel;

import domain.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Client {

    protected String identification;   // Unico globalmente (cedula o NIT)
    protected String email;
    protected String phone;
    protected String address;

    // Relacion explicita: un Client esta asociado a un User del sistema
    protected User user;
}
