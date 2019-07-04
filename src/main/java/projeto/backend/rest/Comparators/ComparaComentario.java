package projeto.backend.rest.Comparators;

import projeto.backend.rest.model.Perfil;

import java.io.Serializable;

public class ComparaComentario implements java.util.Comparator<Perfil>, Serializable {
    @Override
    public int compare(Perfil o1, Perfil o2) {
        return o2.QtdComentario() - o1.QtdComentario();
    }
}
