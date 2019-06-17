package projeto.backend.rest.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import projeto.backend.rest.model.Usuario;
import org.springframework.web.bind.annotation.*;
import projeto.backend.rest.services.UserService;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping({"/v1/auth"})
public class LoginController {

    private final String TOKEN_KEY = "banana";

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody Usuario user) throws ServletException {

        // Recupera o usuario
        Usuario authUser = userService.findByLogin(user.getPassword());

        // verificacoes
        if(authUser == null) {
            throw new ServletException("Usuario nao encontrado!");
        }

        if(!authUser.getPassword().equals(user.getPassword())) {
            throw new ServletException("Senha invalida!");
        }

        String token = Jwts.builder().
                setSubject(authUser.getEmail()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                .compact();

        return new LoginResponse(token);


    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }

}
