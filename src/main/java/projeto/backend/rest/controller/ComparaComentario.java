package projeto.backend.rest.controller;

import projeto.backend.rest.model.Perfil;

import java.io.Serializable;

public class ComparaComentario implements java.util.Comparator<Perfil>, Serializable {
    @Override
    public int compare(Perfil o1, Perfil o2) {
        return o2.getComentarios().size() - o1.getComentarios().size();
    }
}
