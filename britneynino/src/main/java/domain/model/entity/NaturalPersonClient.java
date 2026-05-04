package domain.model.entity;

import domain.model.abstractmodel.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class NaturalPersonClient extends Client {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;   // Validacion: debe ser mayor de 18 anos
}
