package projeto.backend.rest.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String disciplina;
    @OneToMany
    private List<Comentario> comentarios;
    @OneToMany
    private List<Usuario> like;
    @OneToMany
    private List<Nota> notas;


    public Perfil(){

    }

    public Perfil(String disciplina, List<Comentario> comentarios, List<Usuario> like, List<Nota> notas) {
        this.disciplina = disciplina;
        this.comentarios = comentarios;
        this.like = like;
        this.notas = notas;
    }



}
