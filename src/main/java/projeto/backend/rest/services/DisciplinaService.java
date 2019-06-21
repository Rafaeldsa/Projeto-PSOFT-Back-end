package projeto.backend.rest.services;

import org.springframework.stereotype.Service;
import projeto.backend.rest.dao.DisciplinaDAO;
import projeto.backend.rest.dao.UserDAO;
import projeto.backend.rest.model.Disciplina;

import java.util.*;

@Service
public class DisciplinaService {

    private final DisciplinaDAO disciplinaDAO;

    public List<Disciplina>  createAll(List<Disciplina> listDisciplina) {
            return disciplinaDAO.saveAll(listDisciplina);
    }

    DisciplinaService(DisciplinaDAO disciplinaDAO) {
        this.disciplinaDAO = disciplinaDAO;
    }

    public Disciplina findById(long disciplinaId) {
        return disciplinaDAO.findById(disciplinaId);
    }

    public List<Disciplina> findAll() {
        return disciplinaDAO.findAll();
    }

    public List<Disciplina> finBySubstring(String substring) {
        List<Disciplina> disciplinas = disciplinaDAO.findAll();
        List<Disciplina> result = new ArrayList<>();
        for (Disciplina d : disciplinas) {

            if(d.getNome().contains(substring)) {
                result.add(d);
            }

        }
        return  result;
    }
}


