package projeto.backend.rest.services;

import org.springframework.stereotype.Service;
import projeto.backend.rest.dao.UserDAO;
import projeto.backend.rest.model.Usuario;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Usuario create(Usuario usuario)  {
        usuario.setLogin(usuario.getLogin().toLowerCase());
        Usuario userVerify = findByLogin(usuario.getLogin());

        if (!(userVerify == null)) {
            throw new RuntimeException("Email Já Cadastrado");
        }

        return userDAO.save(usuario);
    }

    public Usuario findByLogin(String userLogin) {
        return (userDAO.findByLogin(userLogin));
    }

    public List<Usuario> findAll() {
        return userDAO.findAll();
    }


}
