package projeto.backend.rest.model;

import lombok.Data;


import java.time.ZonedDateTime;
import javax.persistence.*;
import java.util.List;
import java.time.ZoneId;


@Entity
public class Comentario {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @OneToOne
   private Usuario usuario;

   private String comentario;
   private String date;
   private String hora;

   @OneToMany
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


}
