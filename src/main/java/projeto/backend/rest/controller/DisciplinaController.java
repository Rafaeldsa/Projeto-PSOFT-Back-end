package projeto.backend.rest.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import projeto.backend.rest.model.Disciplina;
import projeto.backend.rest.services.DisciplinaService;

import java.util.List;

@RestController
@RequestMapping({"/v1/disciplina"})
public class DisciplinaController {

    private DisciplinaService disciplinaService;

    DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @RequestMapping(value = "/allSubjects", method = RequestMethod.GET)
    public ResponseEntity<List<Disciplina>> findAll() {

        try {
            List<Disciplina> result = disciplinaService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/findSubjects")
    public ResponseEntity<List<Disciplina>> findDisciplina(@RequestBody String substring) {

        if (substring == null || substring.trim().equals("")) {
            throw new InternalError("Something went wrong");
        }

        try {
            List<Disciplina> result = disciplinaService.finBySubstring(substring);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


}
