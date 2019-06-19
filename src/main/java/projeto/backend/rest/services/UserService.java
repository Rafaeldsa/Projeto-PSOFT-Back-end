package projeto.backend.rest.services;

import org.springframework.stereotype.Service;
import projeto.backend.rest.dao.UserDAO;


import projeto.backend.rest.model.Usuario;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;


    UserService(UserDAO userDAO)  {
        this.userDAO = userDAO;
    }

    public Usuario create(Usuario usuario)  {
        if(usuario.getFirstName() == null || usuario.getFirstName().trim().equals("")) {
            throw new NullPointerException("Campo Name não pode ser nulo ou vazio!");
        }

        if(usuario.getLastName() == null || usuario.getLastName().trim().equals("")) {
            throw new NullPointerException("Campo Name não pode ser nulo ou vazio!");
        }

        if(usuario.getEmail() == null || usuario.getEmail().trim().equals("")) {
            throw new NullPointerException("Campo E-mail não pode ser nulo ou vazio!");
        }

        if(usuario.getPassword() == null || usuario.getPassword().trim().equals("")) {
            throw new NullPointerException("Campo Senha não pode ser nulo ou vazio!");
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
