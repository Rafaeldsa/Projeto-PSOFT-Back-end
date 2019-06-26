package projeto.backend.rest.services;

import org.springframework.stereotype.Service;
import projeto.backend.rest.dao.ComentarioDAO;
import projeto.backend.rest.model.Comentario;

import java.util.List;

@Service
public class ComentarioService {

    private ComentarioDAO comentarioDAO;

    ComentarioService(ComentarioDAO comentarioDAO) {
        this.comentarioDAO = comentarioDAO;
    }

    public Comentario save(Comentario comentario) {
        return comentarioDAO.save(comentario);
    }
    public Comentario findById(long comentarioId) {
        return (comentarioDAO.findById(comentarioId));
    }

    public List<Comentario> findAll() {
        return comentarioDAO.findAll();
    }
}
