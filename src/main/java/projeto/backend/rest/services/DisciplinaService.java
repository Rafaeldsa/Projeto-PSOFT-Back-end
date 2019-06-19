package projeto.backend.rest.services;

import org.springframework.stereotype.Service;
import projeto.backend.rest.dao.DisciplinaDAO;
import projeto.backend.rest.dao.UserDAO;
import projeto.backend.rest.model.Disciplina;

import java.util.*;

@Service
public class DisciplinaService {

    private final DisciplinaDAO disciplinaDAO;

    DisciplinaService(DisciplinaDAO disciplinaDAO) {
        this.disciplinaDAO = disciplinaDAO;
    }

    public Disciplina findById(long disciplinaId) {
        return (disciplinaDAO.findById(disciplinaId));
    }

    public List<Disciplina> findAll() {
        return disciplinaDAO.findAll();
    }

    public List<Disciplina> finBySubstring(String substring) {
        List<Disciplina> disciplinas = disciplinaDAO.findAll();
        List<Disciplina> result = new ArrayList<Disciplina>();
        for (Disciplina d : disciplinas) {
            if(d.getName().contains(substring)) {
                result.add(d);
            }
        }
        return  result;
    }
}


