package projeto.backend.rest.model;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
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
