package projeto.backend.rest.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@ApiModel("Perfil -> Uma classe que representa a entidade perfil")
@Entity
public class Perfil {

    @ApiModelProperty(value = "Representa o id do perfil")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ApiModelProperty(value = "Representa o nome da disciplina deste Perfil")
    private String disciplina;

    @ApiModelProperty(value = "Representa os comentários deste Perfil")
    @OneToMany(cascade=CascadeType.ALL)
    private List<Comentario> comentarios;

    @ApiModelProperty(value = "Representa as curtidas deste Perfil")
    @ManyToMany
    @JoinTable(name = "likes", joinColumns = {@JoinColumn(name = "perfil_id")}, inverseJoinColumns = {@JoinColumn(name = "usuario_email")})
    private List<Usuario> curtidas;

    @ApiModelProperty(value = "Representa um usuário atual do Perfil")
    @Transient
    private Usuario userAtual;

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

    public void setCurtidas(Usuario u){
        if(this.curtidas.contains(u)) {
          this.curtidas.remove(u);
        }
        else {
            this.curtidas.add(u);
        }
    }
    public Usuario getUserAtual() {
        return this.userAtual;
    }

    public void setUserAtual(Usuario usuario) {
        this.userAtual = usuario;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public boolean getUserLIke(Usuario user) {
        return this.curtidas.contains(user);
    }

    public int QtdComentario(){
        int result = 0;
        if(!getComentarios().isEmpty()){
            result = qtdRecursivo(this);
        }
        return result;
    }

    private int qtdRecursivo(Perfil perfil) {
        int result = 0;
        if(!perfil.getComentarios().isEmpty()){
            for (Comentario c: perfil.getComentarios()) {
                if(!c.isApagado()) {
                    result += 1 + c.contaResposta();
                }
            }
        }
        return result;
    }
}


