package projeto.backend.rest.Comparators;

import projeto.backend.rest.model.Comentario;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorComentario implements Comparator<Comentario>, Serializable {

    @Override
    public int compare(Comentario o1, Comentario o2) {
        return (int) (o2.getId() - o1.getId());
    }
}
