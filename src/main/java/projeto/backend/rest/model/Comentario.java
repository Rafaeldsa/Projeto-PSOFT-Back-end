package projeto.backend.rest.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Comentario {

   private final Calendar c = Calendar.getInstance();


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @OneToOne
   private Usuario usuario;

   private String comentario;
   private Date data;

   @OneToMany
   private List<Comentario> comentarioDocomentario;

   public Comentario(){

   }
   public Comentario(String comentario, List<Comentario> comentarioDocomentario, Usuario usuario) {
      this.comentarioDocomentario = comentarioDocomentario;
      this.comentario = comentario;
      this.data = c.getTime();
      this.usuario = usuario;
   }




}
