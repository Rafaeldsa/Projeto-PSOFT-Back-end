package projeto.backend.rest.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projeto.backend.rest.model.Disciplina;

import java.util.List;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, String> {


    List<Disciplina> saveAll(Iterable disciplinaLista);

    Disciplina findById(long id);


    List<Disciplina> findAll();


}
