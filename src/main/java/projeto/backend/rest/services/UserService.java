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
        if(usuario.getFirstName() == null) {
            throw new NullPointerException("Este campo não pode ser nulo!");
        }

        if(usuario.getLastName() == null) {
            throw new NullPointerException("Este campo não pode ser nulo!");
        }

        if(usuario.getEmail() == null) {
            throw new NullPointerException("Este campo não pode ser nulo!");
        }

        if(usuario.getPassword() == null) {
            throw new NullPointerException("Este campo não pode ser nulo!");
        }

        usuario.setEmail(usuario.getEmail().toLowerCase());
        Usuario userVerify = findByLogin(usuario.getEmail());

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
