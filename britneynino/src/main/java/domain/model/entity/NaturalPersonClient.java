package application.model.entity;

import java.util.Date;
import application.model.abstractmodel.Client;
import.lombok.Getter;
import.lombok.Setter;

@getter
@setter

public class NaturalPersonClient extends Client {

    private Date birthDate;

}