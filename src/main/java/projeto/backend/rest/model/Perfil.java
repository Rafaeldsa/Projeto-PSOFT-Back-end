package projeto.backend.rest.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



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
    private Integer qtdLikes;
    private boolean flagLike;
    public Perfil(){

    }



    public Perfil(String disciplina, List<Comentario> comentarios, List<Usuario> like) {
        this.disciplina = disciplina;
        this.comentarios = comentarios;
        this.like = like;
        this.flagLike = false;
        this.qtdLikes = 0;
    }

    public void setComentarios(Comentario c) {
        this.comentarios.add(c);
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setQtdLikes(Integer qtdLikes) {
        this.qtdLikes = qtdLikes;
    }

    public void addLike() {
        this.qtdLikes++;
    }
    public void retiraLike() {
        this.qtdLikes--;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public Integer getQtdLikes() {
        return qtdLikes;
    }

    public boolean getLikeUser(Usuario u){
        boolean result;
        if(this.like.contains(u)) {
            this.like.remove(u);
            result =  true;
        }
        else {
            this.like.add(u);
            result = false;
        }
        return result;
    }

    public void setFlagLike(boolean flagLike) {
        this.flagLike = flagLike;
    }

    public boolean isFlagLike() {
        return flagLike;
    }
}
