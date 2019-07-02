package projeto.backend.rest.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.backend.rest.model.*;
import projeto.backend.rest.services.*;

import javax.servlet.ServletException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping({"/v1/disciplina"})
public class DisciplinaController {

    private UserService userService;
    private DisciplinaService disciplinaService;
    private PerfilService perfilService;
    private ComentarioService comentarioService;

    DisciplinaController(DisciplinaService disciplinaService, PerfilService perfilService, UserService userService, ComentarioService comentarioService) {
        this.perfilService = perfilService;
        this.disciplinaService = disciplinaService;
        this.userService = userService;
        this.comentarioService = comentarioService;
    }

    @GetMapping(value = "/allSubjects")
    public ResponseEntity<List<Disciplina>> findAllSubject() {

        try {
            List<Disciplina> result = disciplinaService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/findSubjects")
    public ResponseEntity<List<Disciplina>> findDisciplina(@RequestParam(name = "substring", required = false, defaultValue = "") String substring) {

        if (substring == null || substring.trim().equals("")) {
            throw new InternalError("Something went wrong");
        }


        try {
            List<Disciplina> result = disciplinaService.finBySubstring(substring.toUpperCase());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PostMapping(value = "/createSubject")
    public ResponseEntity<List<Disciplina>> create(@RequestBody List<Disciplina> listDisciplina) {
        List<Disciplina> result = disciplinaService.createAll(listDisciplina);

        if (listDisciplina.isEmpty()) {
            throw new InternalError("Something went wrong");
        }

        try {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/criaPerfil")
    public ResponseEntity<List<Perfil>> createPerfil() {
        List<Perfil> perfis = perfilService.createAll();

        try {
            return ResponseEntity.status(HttpStatus.OK).body(perfis);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/perfis")
    public ResponseEntity<List<Perfil>> findAllPerfil() {
        try {
            List<Perfil> result = perfilService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/getPerfil")
    public ResponseEntity<Perfil> findById(@RequestParam(name = "id", required = false, defaultValue = "") long id) {
        Perfil perfil = perfilService.findById(id);

        if (perfil == null) {
            throw new RuntimeException("Perfil not found");
        }

        return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
    }

    @RequestMapping(value = "/addComentario")
    public ResponseEntity<String> comentar(@RequestParam(name = "id", required = false, defaultValue = "") int id, @RequestBody Comentario comentario, @RequestHeader(name = "authorization", required = false, defaultValue = "") String authorization) throws ServletException {
        Perfil p = perfilService.comentar(id, comentario, authorization);
        try {
            return new ResponseEntity<String>("Comentário criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @DeleteMapping(value = "deleteComentario")
    public ResponseEntity<String> deleteComentario(@RequestParam(name = "id", required = false, defaultValue = "") int idPerfil, @RequestParam(name = "id", required = false, defaultValue = "") int idComentario, @RequestHeader(name = "authorization", required = false, defaultValue = "") String authorization) throws ServletException {
        Perfil p = perfilService.deleteComentario(idPerfil, idComentario, authorization);
        try {
            return new ResponseEntity<String>("Comentário deletado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping(value = "/like")
    public ResponseEntity<Perfil> darLike(@RequestParam(name = "id", required = false, defaultValue = "") long id, @RequestHeader(name = "authorization", required = false, defaultValue = "") String authorization) throws ServletException {
        Perfil p = perfilService.like(id, authorization);
        try {
            return new ResponseEntity<Perfil>(p, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /*@PostMapping(value = "/addResposta")
    public ResponseEntity<Perfil> resposta(@RequestParam(name = "id", required = false, defaultValue = "") long idPerfil, @RequestParam(name = "id", required = false, defaultValue = "") long idComenatrio, @RequestHeader(name = "authorization", required = false, defaultValue = "") String authorization) throws ServletException {
        TokenFilter tk = new TokenFilter();
        String uEmail = tk.getAuth(authorization);
        Usuario user = userService.findByLogin(uEmail);
        Perfil perfil = perfilService.findById(idPerfil);
        Comentario comentario = comentarioService.findById(idComenatrio);
        comentario.getComentarioDocomentario().add()

        try {
            return new ResponseEntity<Perfil>(p, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }*/
}




















