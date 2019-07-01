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


    public Perfil(){

    }



    public Perfil(String disciplina, List<Comentario> comentarios, List<Usuario> like) {
        this.disciplina = disciplina;
        this.comentarios = comentarios;
        this.like = like;
    }

    public void setComentarios(Comentario c) {
        this.comentarios.add(c);
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }


    public String getDisciplina() {
        return disciplina;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public Integer getQtdLikes() {
        return this.like.size();
    }

    public boolean getLikeUser(Usuario u){
        boolean result;
        if(this.like.contains(u)) {
          result =  this.like.remove(u);

        }
        else {
            result = this.like.add(u);

        }
        return result;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


}
