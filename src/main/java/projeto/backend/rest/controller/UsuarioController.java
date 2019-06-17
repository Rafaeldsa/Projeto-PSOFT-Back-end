package projeto.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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