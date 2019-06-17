package projeto.backend.rest.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Usuario {

    @Id
    private String login;

    private String name;
    private String lastName;
    private String senha;

    public Usuario() {

    }

    public Usuario(String login, String name, String lastName, String senha) {
        this.name = name;
        this.lastName = lastName;
        this.senha = senha;
        this.login = login;
    }
}
