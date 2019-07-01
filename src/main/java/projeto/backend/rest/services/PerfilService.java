package projeto.backend.rest.services;

import org.springframework.stereotype.Service;
import projeto.backend.rest.dao.PerfilDAO;
import projeto.backend.rest.model.*;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilService {

    private final PerfilDAO perfilDAO;
    private final DisciplinaService disciplinaService;
    private final UserService userService;

    PerfilService(PerfilDAO perfilDAO, DisciplinaService disciplinaService, UserService userService) {
        this.disciplinaService = disciplinaService;
        this.perfilDAO = perfilDAO;
        this.userService = userService;
    }
    public Perfil save(Perfil p){
        return perfilDAO.save(p);
    }

    public List<Perfil>  createAll() {
        List<Disciplina> listaDisciplina = disciplinaService.findAll();
        List<Comentario> comentarios = new ArrayList<>();
        List<Usuario> likes = new ArrayList<>();
        List<Perfil> perfis = new ArrayList<>();
        for(Disciplina d : listaDisciplina) {
            Perfil p = new Perfil(d.getNome(), comentarios, likes);
            perfis.add(p);
            disciplinaService.findById(d.getId()).setPerfil(p);
            perfilDAO.save(p);

        }
        
        return perfilDAO.saveAll(perfis);
    }
    public Perfil findById(long Id) {
        return perfilDAO.findById(Id);
    }
    public List<Perfil> findAll() {
        return perfilDAO.findAll();
    }

    public Perfil like(long id, String authorization) throws ServletException {
        TokenFilter tk = new TokenFilter();
        String uEmail = tk.getAuth(authorization);
        Usuario user = userService.findByLogin(uEmail);
        Perfil p = perfilDAO.findById(id);
        p.setLikeUser(user);
        return perfilDAO.save(p);
    }
}
