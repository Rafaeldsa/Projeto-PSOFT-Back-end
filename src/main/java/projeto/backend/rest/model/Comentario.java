package projeto.backend.rest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Comentario {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   private String comentario;



   public Comentario(){

   }
   public Comentario(String comentario) {
       this.comentario = comentario;
   }

}
