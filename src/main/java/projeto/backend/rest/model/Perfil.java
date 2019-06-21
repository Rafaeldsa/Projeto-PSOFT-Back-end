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

    private  Double media;
    private long qtdLikes;

    public Perfil(){

    }

    public Perfil(String disciplina, List<Comentario> comentarios, List<Usuario> like, List<Nota> notas) {
        this.disciplina = disciplina;
        this.comentarios = comentarios;
        this.like = like;
        this.notas = notas;
        this.media = 0.0;
        qtdLikes = 0;
    }

    public Double getMedia(List<Nota> notas) {
        notas = this.notas;
        Double soma = 0.0;
        if(notas.isEmpty()) {
            throw new InternalError("Lista notas está vazia");
        }
        for(Nota n : notas) {
           soma += n.getNota();
        }
        this.media = soma / notas.size();
        return  this.media;
    }

}
