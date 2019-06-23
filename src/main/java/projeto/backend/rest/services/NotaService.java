package projeto.backend.rest.services;


import org.springframework.stereotype.Service;
import projeto.backend.rest.dao.NotaDAO;
import projeto.backend.rest.model.Nota;

import java.util.List;

@Service
public class NotaService {

    private NotaDAO notaDAO;

    NotaService(NotaDAO notaDAO) {
        this.notaDAO = notaDAO;
    }

    public Nota create(Nota nota)
    {
        return notaDAO.save(nota);
    }
    public Nota findById(long notaId) {
        return (notaDAO.findById(notaId));
    }

    public List<Nota> findAll() {
        return notaDAO.findAll();
    }

}
