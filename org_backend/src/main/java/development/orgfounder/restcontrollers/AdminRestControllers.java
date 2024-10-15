package development.orgfounder.restcontrollers;


import development.orgfounder.db.entities.*;
import development.orgfounder.db.repositories.*;
import development.orgfounder.services.*;
import development.orgfounder.services.Models.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("apis/adm/")
public class AdminRestControllers {

    @Autowired
    ParametrizacaoService parametrizacaoService;

    @Autowired
    ControleAcessoService controleAcessoService;

    @Autowired
    DonatarioService donatarioService;

    @Autowired
    FuncionarioService funcionarioService;


    @Autowired
    EntregaService entregaService;

    @Autowired
    ProdutoService produtoService;


    @GetMapping("teste-conexao")
    public String testeConexao() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");
        String dataHoraFormatada = agora.format(formatter);

        System.out.println("teste "+dataHoraFormatada);
        return "conectado";
    }


    // ======================= PARAMETRIZAÇÃO =========================//


    /*
    @GetMapping("get-parametrizacao")
    public ResponseEntity<Object> getParametrizacao()
    {
        System.out.println("entrou aqui entrou");

        Parametrizacao parametrizacao = parametrizacaoService.getById(1L);//tenta acessar a tabela de parametrezição

        if(parametrizacao==null)
            parametrizacao=new Parametrizacao();
        return new ResponseEntity<>(parametrizacao, HttpStatus.OK);
    }*/

    @PostMapping(value="add-parametrizacao")
    public ResponseEntity<Object> salvarParametrizacao(@RequestParam(value="razaoP") String razao,
                                                       @RequestParam(value="nomeP") String nome,
                                                       @RequestParam(value="cnpjP") String cnpj,
                                                       @RequestParam(value="emailP") String email,
                                                       @RequestParam(value="siteP") String site,
                                                       @RequestParam(value="cepP") String cep,
                                                       @RequestParam(value="cidadeP") String cidade,
                                                       @RequestParam(value="enderecoP") String rua,
                                                       @RequestParam(value="bairroP") String bairro,
                                                       @RequestParam(value="estadoP") String estado,
                                                       @RequestParam(value="arquivo1") MultipartFile file_upload,
                                                       @RequestParam(value="arquivo2") MultipartFile file_upload2,
                                                       @RequestHeader("Authorization") String token) {
        try{

            Parametrizacao parametrizacao = new Parametrizacao();

            String nomePasta = "\\image";
            String paraConcat, paraConcat2;

            System.out.println(parametrizacaoService.getStaticPath());

            File folderImage = new File(parametrizacaoService.getStaticPath()+nomePasta);

            if(!folderImage.exists())
                folderImage.mkdir();

            if(file_upload==null || file_upload.isEmpty() || file_upload2==null || file_upload2.isEmpty())
            {
                return ResponseEntity.badRequest().body("Arquivo não inserido");
            }
            else
            {
                paraConcat = nomePasta+"\\"+parametrizacaoService.tirarEspaco(file_upload.getOriginalFilename());
                paraConcat2= nomePasta+"\\"+parametrizacaoService.tirarEspaco(file_upload2.getOriginalFilename());
                System.out.println(paraConcat+"  "+paraConcat2);
                System.out.println("teste efetivo de acesso!!");

                parametrizacao.setId(1L);
                parametrizacao.setRazaoSocial(razao);
                parametrizacao.setNomeFantasia(nome);
                parametrizacao.setCnpj(cnpj);
                parametrizacao.setEmail(email);
                parametrizacao.setSite(site);
                parametrizacao.setCep(cep);
                parametrizacao.setCidade(cidade);
                parametrizacao.setRua(rua);
                parametrizacao.setBairro(bairro);
                parametrizacao.setEstado(estado);
                parametrizacao.setLogotipoG(paraConcat);
                parametrizacao.setLogotipoP(paraConcat2);

                paraConcat = parametrizacaoService.tirarEspaco(file_upload.getOriginalFilename());
                paraConcat2 = parametrizacaoService.tirarEspaco(file_upload2.getOriginalFilename());

                System.out.println(paraConcat+" "+paraConcat2);
                //Esse try aqui é pra garantir que se não salvar a denuncia no banco não salva a imagem na parte static
                try
                {
                    parametrizacaoService.save(parametrizacao);

                    Files.copy(file_upload.getInputStream(),
                            Paths.get(folderImage.getAbsolutePath()).resolve(paraConcat), StandardCopyOption.REPLACE_EXISTING);

                    Files.copy(file_upload2.getInputStream(),
                            Paths.get(folderImage.getAbsolutePath()).resolve(paraConcat2), StandardCopyOption.REPLACE_EXISTING);

                }catch(Exception e){return ResponseEntity.badRequest().body("Erro "+e.getMessage());}
            }
        }catch(Exception e){return ResponseEntity.badRequest().body("Erro "+e.getMessage());}

        return ResponseEntity.ok("INSERIDO");
    }

    @GetMapping("get-parametrizacao")
    public ResponseEntity<Object> buscarParametrizacao(@RequestHeader("Authorization") String token)
    {
        // passo 1L porque só vai ter 1 tabela la, caso não tenha nenhuma ele retorna objeto vazio e o ID vai ser 0 que eu verifico no script..
        return new ResponseEntity<>(parametrizacaoService.getById(1L), HttpStatus.OK);
    }


    @PostMapping("add-controleacesso")
    public ResponseEntity<Object> salvarControleAcesso(@RequestParam(value="nome") String nome,
                                                       @RequestParam(value="emailU") String email,
                                                       @RequestParam(value="passU") String senha,
                                                       @RequestParam(value="notify", required = false) String notify)
    {
        System.out.println("teste1");
        ControleAcesso controleAcesso = new ControleAcesso();

        controleAcesso.setLogin(email);
        controleAcesso.setNome(nome);
        controleAcesso.setSenha(senha);

        return new ResponseEntity<>(controleAcessoService.save(controleAcesso), HttpStatus.OK);
    }

    @PostMapping("get-donatario-cpf")
    public ResponseEntity<Object> buscarDonatarioCPF(@RequestParam(value="cpf2") String cpf)
    {
        System.out.println(cpf);
        if(donatarioService.getByCPF(cpf)==null)
            return new ResponseEntity<>("Nao cadastrado",HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(donatarioService.getByCPF(cpf), HttpStatus.OK);
    }

    @PostMapping("get-funcionario-cpf")
    public ResponseEntity<Object> buscarFuncionarioCPF(@RequestParam(value="cpf") String cpf)
    {

        System.out.println(cpf);
        if(funcionarioService.getByCPF(cpf)==null)
            return new ResponseEntity<>("Nao cadastrado",HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(funcionarioService.getByCPF(cpf), HttpStatus.OK);
    }


    @GetMapping("get-all-entregas")
    public ResponseEntity<Object> buscarTodasEntregas()
    {
        return new ResponseEntity<>(entregaService.getAll(),HttpStatus.OK);
    }


    @PostMapping("add-entrega")
    public ResponseEntity<Object> adicionarEntrega(
            @RequestParam(value = "idF") String id_func,
            @RequestParam(value = "idD") String id_donat,
            @RequestParam(value = "tipo") String tipo,
            @RequestParam(value = "data") String data,
            @RequestBody List<ProdutoEntrega> produtos) { // Alterado para receber uma lista de ProdutoEntrega

        try {
            Long idFuncionario = Long.parseLong(id_func);
            Long idDonatario = Long.parseLong(id_donat);

            Donatario donatario = donatarioService.getById(idDonatario);
            if (donatario == null) {
                return new ResponseEntity<>("Donatário não encontrado", HttpStatus.NOT_FOUND);
            }

            Funcionario funcionario = funcionarioService.getById(idFuncionario);
            if (funcionario == null) {
                return new ResponseEntity<>("Funcionário não encontrado", HttpStatus.NOT_FOUND);
            }

            // Criação da entrega
            Entrega entrega = new Entrega();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data2 = LocalDate.parse(data, formatter);
            entrega.setId_donatario(donatario);
            entrega.setId_funcionario(funcionario);
            entrega.setTipo(tipo);
            entrega.setStatus("A");
            entrega.setData(data2);

            // Salva a entrega e os produtos associados
            return new ResponseEntity<>(entregaService.save(entrega, produtos), HttpStatus.OK);

        } catch (NumberFormatException e) {
            return new ResponseEntity<>("ID de funcionário ou donatário inválido", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao processar a entrega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
