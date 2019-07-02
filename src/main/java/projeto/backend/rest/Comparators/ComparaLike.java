package projeto.backend.rest.Comparators;

import projeto.backend.rest.model.Perfil;

import java.io.Serializable;
import java.util.Comparator;

public class ComparaLike implements Comparator<Perfil> , Serializable {
    @Override
    public int compare(Perfil o1, Perfil o2) {
        return o2.getQtdLikes() - o1.getQtdLikes();
    }
}
