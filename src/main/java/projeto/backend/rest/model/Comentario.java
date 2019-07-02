package projeto.backend.rest.model;

import lombok.Data;


import java.time.ZonedDateTime;
import javax.persistence.*;
import java.util.List;
import java.time.ZoneId;


@Data
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


}
