package projeto.backend.rest.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Usuario {

    private String firstName;
    private String lastName;
    @Id
    private String email;

    @OneToMany
    @JsonBackReference(value = "perfil")
    private List<Usuario> curtidas;


    @JsonProperty(access =  JsonProperty.Access.WRITE_ONLY)
    private String password;

    public Usuario() {

    }

    public Usuario(String firstName, String lastName, String email, String password,List<Usuario> users) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.curtidas = users;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
