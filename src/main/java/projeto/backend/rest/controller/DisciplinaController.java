package projeto.backend.rest.controller;


import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.backend.rest.model.*;
import projeto.backend.rest.services.DisciplinaService;
import projeto.backend.rest.services.NotaService;
import projeto.backend.rest.services.PerfilService;
import projeto.backend.rest.services.UserService;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/v1/disciplina"})
public class DisciplinaController {

    private UserService userService;
    private DisciplinaService disciplinaService;
    private PerfilService perfilService;
    private NotaService notaService;

    DisciplinaController(DisciplinaService disciplinaService, PerfilService perfilService, UserService userService)  {
        this.perfilService = perfilService;
        this.disciplinaService = disciplinaService;
        this.userService = userService;
        this.notaService = notaService;
    }

    @RequestMapping(value = "/allSubjects", method = RequestMethod.GET)
    public ResponseEntity<List<Disciplina>> findAllSubject() {

        try {
            List<Disciplina> result = disciplinaService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/findSubjects")
    public ResponseEntity<List<Disciplina>> findDisciplina(@RequestParam(name="substring", required=false, defaultValue="") String substring){

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

    @RequestMapping(value = "/createSubject")
    public ResponseEntity<List<Disciplina>> create(@RequestBody List<Disciplina> listDisciplina) {
        List<Disciplina> result = disciplinaService.createAll(listDisciplina);

        if(listDisciplina.isEmpty()) {
            throw new InternalError("Something went wrong");
        }

        try {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/")
    public ResponseEntity<List<Perfil>> createPerfil() {
        List<Perfil> perfis = perfilService.createAll();

      try {
            return ResponseEntity.status(HttpStatus.OK).body(perfis);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/perfis", method = RequestMethod.GET)
    public ResponseEntity<List<Perfil>> findAllPerfil() {
        try {
            List<Perfil> result = perfilService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/addNota")
    public ResponseEntity<Perfil> adicionaNota(@RequestBody long id, @RequestBody Double nota, @RequestHeader(name="authorization", required = false, defaultValue = "") String authorization) throws  ServletException
    {

            TokenFilter tk = new TokenFilter();
            Perfil p = perfilService.findById(id);
            String uEmail = tk.getAuth(authorization);
            Usuario u = userService.findByLogin(uEmail);
            Nota n = new Nota(u, nota);
            p.setNotas(n);
            p.getMedia();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(p);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }
    @GetMapping(value="/{id}")
    public ResponseEntity<Perfil> findById(@PathVariable long id) {
        Perfil perfil = perfilService.findById(id);

        if (perfil == null) {
            throw new RuntimeException("Perfil not found");
        }

        return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
    }

    @PostMapping(value = "/like")
    public ResponseEntity<Perfil> darLike(@RequestBody long id, @RequestBody boolean like, @RequestHeader(name="authorization", required = false, defaultValue = "") String authorization) throws  ServletException {


            TokenFilter tk = new TokenFilter();
            Perfil p = perfilService.findById(id);
            String uEmail = tk.getAuth(authorization);
            Usuario u = userService.findByLogin(uEmail);

            if(like = true) {
                p.getLike().add(u);
                p.addLike();
            }
            else if(like = false) {
                p.getLike().remove(u);
                p.retiraLike();
            }

        try {
            return ResponseEntity.status(HttpStatus.OK).body(p);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}






















