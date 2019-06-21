package projeto.backend.rest.services;

import org.springframework.stereotype.Service;
import projeto.backend.rest.dao.PerfilDAO;
import projeto.backend.rest.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilService {

    private final PerfilDAO perfilDAO;
    private final DisciplinaService disciplinaService;

    PerfilService(PerfilDAO perfilDAO, DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
        this.perfilDAO = perfilDAO;
    }

    public List<Perfil>  createAll() {
        List<Disciplina> listaDisciplina = disciplinaService.findAll();
        List<Comentario> comentarios = new ArrayList<>();
        List<Usuario> likes = new ArrayList<>();
        List<Nota> notas = new ArrayList<>();
        List<Perfil> perfis = new ArrayList<>();
        for(Disciplina d : listaDisciplina) {
            Perfil p = new Perfil(d.getNome(), comentarios, likes, notas);
            perfis.add(p);
            disciplinaService.findById(d.getId()).setPerfil(p);

        }

        return perfilDAO.saveAll(perfis);
    }
    public Perfil findById(long Id) {
        return perfilDAO.findById(Id);
    }
    public List<Perfil> findAll() {
        return perfilDAO.findAll();
    }

}
