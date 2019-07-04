package projeto.backend.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.time.ZonedDateTime;
import javax.persistence.*;
import java.util.List;
import java.time.ZoneId;

@ApiModel("Comentário -> Representa uma classe que representa uma entidade comentário.")
@Data
@Entity
public class Comentario {

   @ApiModelProperty("Representa o id do Comentário.")
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @ApiModelProperty("Representa o usuário que realizou o Comentário.")
   @OneToOne
   private Usuario usuario;
   @ApiModelProperty("Representa o texto do Comentário.")
   private String comentario;
   @ApiModelProperty("Representa a data do Comentário.")
   private String date;
   @ApiModelProperty("Representa a hora do Comentário.")
   private String hora;
   @ApiModelProperty("Representa o status de um Comentário, se ele foi deletado ou não.")
   public boolean apagado;
   @ApiModelProperty("Representa um conjunto de respotas do Comentário.")
   @OneToMany(cascade=CascadeType.ALL)
   private List<Comentario> comentarioDocomentario;

   public Comentario(){

   }
   public Comentario(String comentario, List<Comentario> comentarioDocomentario, Usuario usuario, String data, String hora) {
      this.comentarioDocomentario = comentarioDocomentario;
      this.comentario = comentario;
      this.date = data;
      this.hora = hora;
      this.usuario = usuario;
   }

   public void setComentarioDocomentario(Comentario c) {
      this.comentarioDocomentario.add(c);
   }
   public List<Comentario> getComentarioDocomentario() {
      return comentarioDocomentario;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public Usuario getUsuario() {
      return usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public String getComentario() {
      return comentario;
   }

   public void setComentario(String comentario) {
      this.comentario = comentario;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getHora() {
      return hora;
   }

   public void setHora(String hora) {
      this.hora = hora;
   }

   public int contaResposta() {
      int result = 0;
      if (!getComentarioDocomentario().isEmpty() && !isApagado()) {
         result = qtdRecursivo(this);
      }

      return result;
   }


   private int qtdRecursivo(Comentario comentario) {
      int result = 0;
      if (!comentario.getComentarioDocomentario().isEmpty()) {
         for (Comentario c : comentario.getComentarioDocomentario()) {
            if (!c.isApagado())
               result += 1 + qtdRecursivo(c);
         }
      }
      return result;
   }

   public void setApagado(boolean apagado) {
      this.apagado = apagado;
      if (!getComentarioDocomentario().isEmpty()) {
         apagaRecursivo(getComentarioDocomentario());
      }
   }

   private void apagaRecursivo(List<Comentario> respostas) {
      if (!respostas.isEmpty()) {
         for (Comentario r : respostas) {
            r.setApagado(true);
         }
      }
   }

   public int QtdsComentarios() {
      int result = 0;
      if(!getComentarioDocomentario().isEmpty()){
         for(Comentario c : this.getComentarioDocomentario()) {
            if(!c.isApagado()) {
               result++;
            }
         }

      }
      return result;
   }
}
