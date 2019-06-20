package projeto.backend.rest.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Nota {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Usuario usuario;

    private Double nota;

    public Nota() {

    }

    public Nota(Usuario usuario, Double nota) {
        this.usuario = usuario;
        this.nota = nota;
    }

}
