package projeto.backend.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.backend.rest.model.Perfil;


import java.util.List;


@Repository
public interface PerfilDAO extends JpaRepository<Perfil, String> {

    List<Perfil> saveAll(Iterable perfilList);

    Perfil findById(long id);
    Perfil save(Perfil perfil);

    List<Perfil> findAll();
}
