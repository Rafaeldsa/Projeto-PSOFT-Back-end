package projeto.backend.rest.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import projeto.backend.rest.dao.UserDAO;


import projeto.backend.rest.model.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Service
public class UserService {

    private final UserDAO userDAO;
    private JavaMailSenderImpl javaMailSender;

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

    public void enviaEmail(Usuario usuario)  {
        Properties props = new Properties();

        //Parâmetros de conexão com servidor Gmail
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        javaMailSender.setUsername("ucdbplatform@gmail.com");
        javaMailSender.setPassword("ap1r3st1");
        javaMailSender.setJavaMailProperties(props);

        //criando email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hello from Spring Boot Application");
        message.setTo(usuario.getEmail());
        message.setFrom("ucdbplatform@gmail.com");

        javaMailSender.send(message);
    }
}
