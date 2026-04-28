package application.model.entity;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import application.model.abstractmodel.Client;

@Getter
@Setter
public class NaturalPersonClient extends Client {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}