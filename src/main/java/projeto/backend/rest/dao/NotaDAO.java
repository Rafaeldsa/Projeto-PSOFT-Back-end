package projeto.backend.rest.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.backend.rest.model.Nota;

import java.util.List;

@Repository
public interface NotaDAO extends JpaRepository<Nota, String> {

    Nota save(Nota nota);
    Nota findById(long id);
    List<Nota> findAll();

}
