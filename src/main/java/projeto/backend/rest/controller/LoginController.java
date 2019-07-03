package projeto.backend.rest.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import projeto.backend.rest.model.Usuario;
import org.springframework.web.bind.annotation.*;
import projeto.backend.rest.services.UserService;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping({"/v1/auth"})
public class LoginController {

    private final String TOKEN_KEY = "ninja";

    @Autowired
    private UserService userService;

    @ApiOperation(
            value="Realizar login em um usuário",
            response=Usuario.class,
            notes="Essa método realiza a autenticação de um usuário previamente cadastrado.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna uma response contendo a autenticação do usuário, um token.",
                    response=Usuario.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma Exception"

            )

    })
    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody Usuario user) throws ServletException {

        // Recupera o usuario
        Usuario authUser = userService.findByLogin(user.getEmail());

        // verificacoes
        if(authUser == null) {
            throw new ServletException("Usuario nao encontrado!");
        }

        if(!authUser.getPassword().equals(user.getPassword())) {
            throw new ServletException("Senha invalida!");
        }

        String authorization = Jwts.builder().
                setSubject(authUser.getEmail()).
                signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
                setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000000))
                .compact();

        return new LoginResponse(authorization);


    }

    @ApiModel("LoginResponse -> Representa a resposta da autenticação")
    private class LoginResponse {
        @ApiModelProperty(value = "Representa um token de autenticação do usuário")
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }

}
