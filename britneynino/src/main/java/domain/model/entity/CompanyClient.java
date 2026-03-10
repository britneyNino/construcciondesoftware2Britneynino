package application.model.entity;

import application.model.abstractmodel.Client;
import.lombok.Getter;
import.lombok.Setter;

@getter
@setter

public class CompanyClient extends Client {

    private String companyName;
    private String taxId;

}