package projeto.backend.rest.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import projeto.backend.rest.model.Comentario;

import java.util.List;


@Repository
public interface ComentarioDAO extends JpaRepository<Comentario, String> {

    Comentario save(Comentario Comentario);

    Comentario findById(long id);

    List<Comentario> findAll();

}
