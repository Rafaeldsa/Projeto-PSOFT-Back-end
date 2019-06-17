package projeto.backend.rest.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Usuario {
    private String firstName;
    private String lastName;

    @Id
    private String email;


    private String password;

    public Usuario() {

    }

    public Usuario(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
}
