package projeto.backend.rest.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel("Disciplina -> Uma classe que representa uma entidade disciplina.")
@Data
@Entity
public class Disciplina {

    @ApiModelProperty(value="representa o id da disciplina")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty(value="representa o nome da disciplina")
    private String nome;

    @ApiModelProperty(value="representa o Perfil da disciplina")
    @OneToOne
    private Perfil perfil;


    public Disciplina() {
    }

    public Disciplina(String name, Perfil perfil) {
        this.perfil = perfil;
        this.nome = name;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }
}
