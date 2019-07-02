package projeto.backend.rest.model;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorComentario implements Comparator<Comentario>, Serializable {

    @Override
    public int compare(Comentario o1, Comentario o2) {
        return (int) (o1.getId() - o2.getId());
    }
}
