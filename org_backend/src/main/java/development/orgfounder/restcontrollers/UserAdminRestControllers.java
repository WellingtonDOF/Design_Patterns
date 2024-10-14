package development.orgfounder.restcontrollers;

import development.orgfounder.db.entities.ControleAcesso;
import development.orgfounder.db.entities.Estoque;
import development.orgfounder.security.JWTTokenProvider;
import development.orgfounder.services.ControleAcessoService;
import development.orgfounder.services.ParametrizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value="apis/user/")
public class UserAdminRestControllers {

    @Autowired
    ParametrizacaoService parametrizacaoService;

    @Autowired
    ControleAcessoService controleAcessoService;

    @GetMapping("get-parametrizacao")
    public ResponseEntity<Object> buscarParametrizacao(@RequestHeader("Authorization") String token)
    {
        //if(JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(parametrizacaoService.getById(1L), HttpStatus.OK);
        //else
         //   return new ResponseEntity<>("Não autorizado!",HttpStatus.UNAUTHORIZED);
        // passo 1L porque só vai ter 1 tabela la, caso não tenha nenhuma ele retorna objeto vazio e o ID vai ser 0 que eu verifico no script..
    }


    @PostMapping("add-controleacesso")
    public ResponseEntity<Object> salvarControleAcesso(@RequestParam(value="nome") String nome,
                                                       @RequestParam(value="emailU") String email,
                                                       @RequestParam(value="passU") String senha,
                                                       @RequestParam(value="notify", required = false) String notify)
    {

        ControleAcesso controleAcesso = new ControleAcesso();
        Estoque estoque = new Estoque();

        controleAcesso.setLogin(email);
        controleAcesso.setNome(nome);
        controleAcesso.setSenha(senha);

        if(notify.equals("sim"))
            controleAcesso.setNotify(true);
        else
            controleAcesso.setNotify(false);

        if(controleAcessoService.getByEmail(email)==null)
        {
            controleAcesso = controleAcessoService.save(controleAcesso);
            estoque.attach(controleAcesso);
            return new ResponseEntity<>(controleAcesso, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Nao cadastrado",HttpStatus.CONFLICT);
    }

    @PostMapping("get-Email")
    public ResponseEntity<Object> obterEmail(@RequestParam(value="emailU") String email)

    {
        if(controleAcessoService.getByEmail(email)==null)
            return new ResponseEntity<>("Nao cadastrado",HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(controleAcessoService.getByEmail(email), HttpStatus.OK);
    }

    @PostMapping("deletar-User")
    public ResponseEntity<Object> deletarUsuario(@RequestParam(value="emailU") String email)
    {
        List<ControleAcesso> controleAcessos = controleAcessoService.getAll();
        int i =0;

        for(ControleAcesso controle : controleAcessos) {
            if(controle.getNivel().equals("1") && controle.getStatus().equals("A"))
                i=i+1;
        }

        if(i>1)
        {
            System.out.println("teste");

            ControleAcesso controleAcesso;
            controleAcesso = controleAcessoService.getByEmail(email);

            if(controleAcesso==null)
                return ResponseEntity.notFound().build();
            else
            {
                controleAcesso.setStatus("I");
                controleAcessoService.save(controleAcesso);
                return ResponseEntity.ok().build(); // 200 OK
            }
        }else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); //403
    }
}
