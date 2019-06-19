package projeto.backend.rest.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projeto.backend.rest.model.Disciplina;
import projeto.backend.rest.model.Usuario;

import java.util.List;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, String> {


    @Override
    <S extends Disciplina> List<S> saveAll(Iterable<S> entities);

    @Query(value = "Select d from Disciplina as d where d.id=:did")
    Usuario findByLogin(@Param("did") long id);


    List<Disciplina> findAll();
}
