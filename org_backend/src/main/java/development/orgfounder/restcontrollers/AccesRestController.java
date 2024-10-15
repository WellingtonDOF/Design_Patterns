package development.orgfounder.restcontrollers;

import development.orgfounder.db.entities.ControleAcesso;
import development.orgfounder.security.JWTTokenProvider;
import development.orgfounder.services.ControleAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value="apis/security/")
public class AccesRestController {

    @Autowired
    ControleAcessoService controleacessoService;

    @GetMapping("teste-conexao")
    public String testeConexao() {
        return "conectado";
    }

    @PostMapping("logar/")
    public ResponseEntity<Object> logar(@RequestParam(value="email") String email,
                                           @RequestParam(value="pass") String senha)
    {
        String token ="não autenticado";

        ControleAcesso controleAcesso = controleacessoService.getByEmail(email);

        Map<String, Object> response = new HashMap<>();

        response.put("token",token);//coloca o token no map e caso futuramente dê errado ele vai estar como nao autenticado.

        if(controleAcesso==null)//email nao encontrado
        {
            response.put("success", false); //cria e define o campo sucesso como falso ou seja não deu certo.
            response.put("message","Email não encontrado");//esse campo informa onde foi o erro, email, senha, status..
            response.put("controleAcesso", null);//não retorna o objeto porque nao achou.

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);// retorna o objeto criado e um status 404 (não encontrado)
        }
        else//encontrou o email
        {
            response.put("controleAcesso",controleAcesso);//define o campo controlAcesso com o objeto para retornar ele

            if(controleAcesso.getSenha().equalsIgnoreCase(senha))//se a senha estiver correta entra.
            {
                controleAcesso.setSenha(null);//colocar a senha para null para não expor a senha de quem acessar quando retornar o objeto.

                if(controleAcesso.getStatus().equalsIgnoreCase("A"))//indica que a conta está ativa.
                {
                    response.put("success",true);//seta o campo succcess para verdadeiro indicando que deu certo o login.
                    response.put("message","Login bem-sucedido");

                    token= JWTTokenProvider.getToken(controleAcesso.getLogin(), ""+controleAcesso.getNivel());
                    response.put("token",token);// tem que adicionar o token ao map novamente pq o valor dele foi atualizado.
                    //return ResponseEntity.ok(token); // em vez de retornar o .ok(token) eu retorno ele dentro do response
                    return new ResponseEntity<>(response, HttpStatus.OK);//status 200, (deu certo)
                }
                else//indica que a conta está inativa
                {
                    response.put("success",false);
                    response.put("message","Conta inativa");

                    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);//status 403, acesso não autorizado nesse caso devido ao status inativo
                }
            }
            else
            {
                controleAcesso.setSenha(null);
                response.put("success",false);
                response.put("message","Senha incorreta");

                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);//status 401, acesso não autorizado erro de credencial
            }
        }
    }
}
