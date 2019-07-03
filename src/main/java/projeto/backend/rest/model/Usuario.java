package projeto.backend.rest.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;


@ApiModel("Usuário -> Uma classe que representa uma entidade usuário, que poderá realizar diversas operações na API, a partir de seu cadastro.")
@Entity
public class Usuario {

    @ApiModelProperty(value = "Representa o primeiro nome do usuário")
    private String firstName;

    @ApiModelProperty(value = "Representa o último nome do usuário")
    private String lastName;

    @ApiModelProperty(value = "Representa o email do usuário")
    @Id
    private String email;

    @ApiModelProperty(value = "UMa referência para o atributo curtidas em perfil")
    @OneToMany
    @JsonBackReference(value = "perfil")
    private List<Usuario> curtidas;

    @ApiModelProperty(value = "Representa a senha do usuário")
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
