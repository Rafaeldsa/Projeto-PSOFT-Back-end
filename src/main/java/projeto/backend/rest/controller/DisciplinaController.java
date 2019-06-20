package projeto.backend.rest.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.backend.rest.model.*;
import projeto.backend.rest.services.DisciplinaService;
import projeto.backend.rest.services.PerfilService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/v1/disciplina"})
public class DisciplinaController {

    private DisciplinaService disciplinaService;
    private PerfilService perfilService;

    DisciplinaController(DisciplinaService disciplinaService, PerfilService perfilService) {
        this.perfilService = perfilService;
        this.disciplinaService = disciplinaService;
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


}






















