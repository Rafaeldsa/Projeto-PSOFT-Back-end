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

    @ManyToMany
    @JoinTable(name = "likes", joinColumns = {@JoinColumn(name = "perfil_id")}, inverseJoinColumns = {@JoinColumn(name = "usuario_email")})
    private List<Usuario> curtidas;


    public Perfil(){

    }

    public Perfil(String disciplina, List<Comentario> comentarios, List<Usuario> like) {
        this.disciplina = disciplina;
        this.comentarios = comentarios;
        this.curtidas = like;
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
        return this.curtidas.size();
    }

    public boolean setCurtidas(Usuario u){
        boolean result;
        if(this.curtidas.contains(u)) {
          result =  this.curtidas.remove(u);

        }
        else {
            result = this.curtidas.add(u);

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
