package projeto.backend.rest.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
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
    private Integer qtdLikes;

    public Perfil(){

    }

    public Perfil(String disciplina, List<Comentario> comentarios, List<Usuario> like, List<Nota> notas) {
        this.disciplina = disciplina;
        this.comentarios = comentarios;
        this.like = like;
        this.notas = new ArrayList<>();
        this.media = 0.0;
        qtdLikes = 0;
    }

    public Double getMedia() {

        Double soma = 0.0;
       /* if(this.notas.isEmpty()) {
            throw new InternalError("Lista notas está vazia");
        }*/
        for(Nota n : this.notas) {
           soma += n.getNota();
        }
        this.media = soma / this.notas.size();
        return  this.media;
    }


    public void setNotas(Nota nota) {
        this.notas.add(nota);
    }
}
