package projeto.backend.rest.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import projeto.backend.rest.dao.PerfilDAO;
import projeto.backend.rest.model.*;

import javax.servlet.ServletException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilService {

    private final PerfilDAO perfilDAO;
    private final DisciplinaService disciplinaService;
    private final UserService userService;
    private final ComentarioService comentarioService;

    PerfilService(PerfilDAO perfilDAO, DisciplinaService disciplinaService, UserService userService, ComentarioService comentarioService) {
        this.disciplinaService = disciplinaService;
        this.perfilDAO = perfilDAO;
        this.userService = userService;
        this.comentarioService = comentarioService;
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

    public Perfil comentar(long id, Comentario comentario, String authorization) throws ServletException{
        ZonedDateTime date = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        String data = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);
        String hora = DateTimeFormatter.ofPattern("hh:mm").format(date);
        TokenFilter tk = new TokenFilter();
        String uEmail = tk.getAuth(authorization);
        Usuario u = userService.findByLogin(uEmail);
        Comentario c = comentario;
        c.setUsuario(u);
        c.setDate(data);
        c.setHora(hora);
        comentarioService.save(c);
        Perfil p = perfilDAO.findById(id);
        p.setComentarios(c);
        perfilDAO.save(p);

        return p;
    }

    public Perfil deleteComentario(long idPerfil,long idComentario, String authorization) throws ServletException{
        TokenFilter tk = new TokenFilter();
        String uEmail = tk.getAuth(authorization);
        Usuario u = userService.findByLogin(uEmail);
        Perfil p = perfilDAO.findById(idPerfil);
        p.getComentarios().remove(idComentario);
        perfilDAO.save(p);

        return p;

    }


    public Perfil respostaComentario(long idPerfil, long idComentario, Comentario comentarioResposta, String authorization) throws ServletException {
        ZonedDateTime date = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        String data = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);
        String hora = DateTimeFormatter.ofPattern("hh:mm").format(date);

        TokenFilter tk = new TokenFilter();
        String uEmail = tk.getAuth(authorization);
        Usuario user = userService.findByLogin(uEmail);

        Perfil perfil = perfilDAO.findById(idPerfil);

        Comentario c = perfil.getComentarios().get((int) idComentario);

        comentarioResposta.setUsuario(user);
        comentarioResposta.setDate(data);
        comentarioResposta.setHora(hora);

        c.setComentarioDocomentario(comentarioResposta);

        comentarioService.save(c);
        perfilDAO.save(perfil);

        return perfil;
    }

}
