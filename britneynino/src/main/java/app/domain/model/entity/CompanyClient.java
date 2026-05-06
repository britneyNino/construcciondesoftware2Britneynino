package application.model.entity;

import lombok.Getter;
import lombok.Setter;
import application.model.abstractmodel.Client;

@Getter
@Setter
public class CompanyClient extends Client {

    private String companyName;
    private String nit;
}