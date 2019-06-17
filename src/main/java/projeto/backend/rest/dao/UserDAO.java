package projeto.backend.rest.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projeto.backend.rest.model.Usuario;

import java.util.List;


@Repository
public interface UserDAO extends JpaRepository<Usuario, String> {

    Usuario save(Usuario user);

    @Query(value = "Select u from Usuario as u where u.email=:pemail")
    Usuario findByLogin(@Param("pemail") String email);

    List<Usuario> findAll();

}
