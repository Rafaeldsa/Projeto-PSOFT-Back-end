package projeto.backend.rest.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.backend.rest.model.Disciplina;
import projeto.backend.rest.model.Usuario;
import projeto.backend.rest.services.UserService;

import java.util.List;

@RestController
@RequestMapping({"/v1/users"})
public class UsuarioController {
        private UserService userService;

        UsuarioController(UserService userService) {
            this.userService = userService;
        }

    @ApiOperation(
            value="Cadastrar um novo usuario",
            response=ResponseEntity.class,
            notes="Essa operação salva um novo registro com as informações do usuario.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um ResponseModel com uma mensagem de sucesso",
                    response=ResponseEntity.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro vamos retornar um ResponseModel com a Exception",
                    response=ResponseEntity.class
            )

    })
        @PostMapping(value = "/")
        @ResponseBody
        public ResponseEntity<Usuario> create(@RequestBody Usuario user) {

            Usuario newUser = userService.create(user);

            if (newUser == null) {
                throw new InternalError("Something went wrong");
            }
                return new ResponseEntity<Usuario>(newUser, HttpStatus.CREATED);
        }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> findAll() {

        try {
            List<Usuario> result = userService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



}
