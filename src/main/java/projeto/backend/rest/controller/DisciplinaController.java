package projeto.backend.rest.controller;



import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.backend.rest.Comparators.ComparaComentario;
import projeto.backend.rest.Comparators.ComparaLike;
import projeto.backend.rest.Comparators.ComparatorComentarioId;
import projeto.backend.rest.model.*;
import projeto.backend.rest.services.*;

import javax.servlet.ServletException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping({"/v1/disciplina"})
public class DisciplinaController {

    private UserService userService;
    private DisciplinaService disciplinaService;
    private PerfilService perfilService;
    private ComentarioService comentarioService;
    private Comparator<Perfil> comparadorRanking;
    private ComparatorComentarioId comparadorComentario;

    DisciplinaController(DisciplinaService disciplinaService, PerfilService perfilService, UserService userService, ComentarioService comentarioService) {
        this.perfilService = perfilService;
        this.disciplinaService = disciplinaService;
        this.userService = userService;
        this.comentarioService = comentarioService;
        this.comparadorRanking = new ComparaLike();
        this.comparadorComentario = new ComparatorComentarioId();
    }
    @ApiOperation(
            value="Lista todas as disciplinas.",
            response=Disciplina.class,
            notes="Realiza a listagem de todas as disciplinas existentes.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna uma lista com todas as disciplinas existentes.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )

    })
    @GetMapping(value = "/allSubjects")
    public ResponseEntity<List<Disciplina>> findAllSubject() {

        try {
            List<Disciplina> result = disciplinaService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(
            value="Procurar disciplinas.",
            response=Disciplina.class,
            notes="Realiza a operação de procurar uma disciplina a partir de uma substring ou id.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna uma lista com as disciplinas que contém a substring ou id passado como parâmetro.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )

    })
    @GetMapping(value = "/findSubjects")
    public ResponseEntity<List<Disciplina>> findDisciplina(@ApiParam("Representa a substring a ser pesquisada")@RequestParam(name = "substring", required = false, defaultValue = "") String substring) {

        if (substring == null || substring.trim().equals("")) {
            throw new InternalError("Something went wrong");
        }


        try {
            List<Disciplina> result = disciplinaService.finBySubstring(substring.toUpperCase());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @ApiOperation(
            value="Cria todas as disciplinas.",
            response=Disciplina.class,
            notes="Cria todas as disciplinas a partir de um json passado como parâmetro.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna uma lista com as todas as disciplinas criadas.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )

    })
    @PostMapping(value = "/createSubject")
    public ResponseEntity<List<Disciplina>> create(@ApiParam("Representa um conjunto de disciplinas")@RequestBody List<Disciplina> listDisciplina) {
        List<Disciplina> result = disciplinaService.createAll(listDisciplina);

        if (listDisciplina.isEmpty()) {
            throw new InternalError("Something went wrong");
        }

        try {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(
            value="Cria todos os perfis.",
            response=Disciplina.class,
            notes="Cria todos os perfis de cada disciplina a partir das disciplinas existentes.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna uma lista com todos os perfis criados.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )

    })
    @PostMapping(value = "/criaPerfil")
    public ResponseEntity<List<Perfil>> createPerfil() {
        List<Perfil> perfis = perfilService.createAll();

        try {
            return ResponseEntity.status(HttpStatus.OK).body(perfis);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(
            value="Lista todos os perfis.",
            response=Disciplina.class,
            notes="Lista todos os perfis de cada disciplina existentes.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna uma lista com todos os perfis já criados.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )

    })
    @GetMapping(value = "/perfis")
    public ResponseEntity<List<Perfil>> findAllPerfil() {
        try {
            List<Perfil> result = perfilService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(
            value="Recupera um perfil.",
            response=Disciplina.class,
            notes="Recuperar um perfil a partir de seu id.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um perfil.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma Exception."

            )
    })
    @GetMapping(value = "/getPerfil")
    public ResponseEntity<Perfil> findById(@ApiParam("Representa o id de um Perfil")@RequestParam(name = "id", required = false, defaultValue = "") long id, @RequestHeader(name = "authorization", required = false, defaultValue = "") String authorization) throws ServletException {
        Perfil perfil = perfilService.getPerfil(id, authorization);

        if (perfil == null) {
            throw new RuntimeException("Perfil not found");
        }
        return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
    }

    @ApiOperation(
            value="Adiciona um comentário.",
            response=Disciplina.class,
            notes="Adiciona um comentário a um perfil.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna uma mensagem: 'Comentário crado com sucesso!'.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )
    })
    @PostMapping(value = "/addComentario")
    public ResponseEntity<String> comentar(@RequestParam(name = "id", required = false, defaultValue = "") int id, @RequestBody Comentario comentario, @RequestHeader(name = "authorization", required = false, defaultValue = "") String authorization) throws ServletException {
        ZonedDateTime date = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        String data = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);
        String hora = DateTimeFormatter.ofPattern("hh:mm").format(date);
        TokenFilter tk = new TokenFilter();
        String uEmail = tk.getAuth(authorization);
        Usuario u = userService.findByLogin(uEmail);
        Comentario c = comentario;
        c.setUsuario(u);
        c.setDate(data);
        c.setHora(hora);
        comentarioService.save(c);
        Perfil p = perfilService.findById(id);
        p.setComentarios(c);
        p.getComentarios().sort(this.comparadorComentario);

        perfilService.save(p);
        try {
            return new ResponseEntity<String>("Comentário criado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @ApiOperation(
            value="Deleta um comentário.",
            response=Disciplina.class,
            notes="Deleta um comentário de um perfil.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um Perfil com este novo comentário.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )
    })
    @DeleteMapping(value = "deleteComentario")
    public ResponseEntity<Perfil> deleteComentario(@RequestParam(name = "idPerfil", required = false, defaultValue = "") long idPerfil, @RequestParam(name = "idComentario", required = false, defaultValue = "") long idComentario, @RequestHeader(name = "authorization", required = false, defaultValue = "") String authorization) throws ServletException {
        Perfil p = perfilService.deleteComentario(idPerfil, idComentario, authorization);
        try {
            return new ResponseEntity<Perfil>(p, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @ApiOperation(
            value="Curtida em um perfil",
            response=Disciplina.class,
            notes="Realiza a operação de dar um like/deslike em um Perfil.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um Perfil com este novo like/deslike.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )
    })
    @PutMapping(value = "/like")
    public ResponseEntity<Perfil> darLike(@RequestParam(name = "id", required = false, defaultValue = "") long id, @RequestHeader(name = "authorization", required = false, defaultValue = "") String authorization) throws ServletException {
        Perfil p = perfilService.like(id, authorization);
        try {
            return new ResponseEntity<Perfil>(p, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @ApiOperation(
            value="Resposta a comentário.",
            response=Disciplina.class,
            notes="Adiciona uma resposta a um determinado comentário de um Perfil.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna um Perfil com esta nova resposta de um comentário.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )
    })
    @PostMapping(value = "/addResposta")
    public ResponseEntity<Perfil> resposta(@RequestParam(name = "idPerfil", required = false, defaultValue = "") int idPerfil, @RequestParam(name = "idComentario", required = false, defaultValue = "") int idComentario,@RequestBody Comentario comentarioResposta, @RequestHeader(name = "authorization", required = false, defaultValue = "") String authorization) throws ServletException {
        ZonedDateTime date = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        String data = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);
        String hora = DateTimeFormatter.ofPattern("hh:mm").format(date);

        TokenFilter tk = new TokenFilter();
        String uEmail = tk.getAuth(authorization);
        Usuario user = userService.findByLogin(uEmail);

        Perfil perfil = perfilService.findById(idPerfil);

        Comentario c = comentarioService.findById(idComentario);

        comentarioResposta.setUsuario(user);
        comentarioResposta.setDate(data);
        comentarioResposta.setHora(hora);

        c.setComentarioDocomentario(comentarioResposta);

        comentarioService.save(c);
        perfilService.save(perfil);
        try {
            return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(
            value="Ranking por like.",
            response=Disciplina.class,
            notes="Criação de um ranking a partir da quantidade de likes dos Perfis.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna uma lista dos perfis rankeado por like.",
                    response=Disciplina.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )
    })
    @GetMapping(value = "/rankingLike")
    public ResponseEntity<List> rankingLike() {
            List<Perfil> result = perfilService.findAll();
        this.comparadorRanking = new ComparaLike();
            result.sort(this.comparadorRanking);
      try {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(
            value="Ranking por Comentário.",
            response=Perfil.class,
            notes="Criação de um ranking a partir da quantidade de comentários dos Perfis.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna uma lista dos perfis rankeado por comentários.",
                    response=Perfil.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro, retornará uma BAD_REQUEST."

            )
    })
    @GetMapping(value = "/rankingComentario")
    public ResponseEntity<List> rankingComentario() {
        List<Perfil> perfis = perfilService.findAll();
        List<Perfil> result = new ArrayList<>();
        for(Perfil p : perfis) {
            for(Comentario c : p.getComentarios()) {
                if(!c.isApagado()) {
                    result.add(p);
                }
            }
        }
        this.comparadorRanking = new ComparaComentario();
        result.sort(this.comparadorRanking);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}




















