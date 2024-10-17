package development.orgfounder.restcontrollers;

import development.orgfounder.db.entities.Doacao;
import development.orgfounder.db.entities.Produto; // Importa a classe Produto
import development.orgfounder.services.DoacaoService;
import development.orgfounder.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;
    @Autowired
    private ProdutoService produtoService;

    // Endpoint para listar todas as doações
    @GetMapping("/listar-doacoes")
    public ResponseEntity<List<Doacao>> listarDoacoes() {
        List<Doacao> doacoes = doacaoService.getAll();
        return ResponseEntity.ok(doacoes);
    }

    // Endpoint para buscar uma doação por ID
    @GetMapping("/{id}")
    public ResponseEntity<Doacao> buscarDoacao(@PathVariable Long id) {
        Doacao doacao = doacaoService.getById(id);
        return ResponseEntity.ok(doacao);
    }

    // Endpoint para criar uma nova doação
    @PostMapping("/criar-doacao")
    public ResponseEntity<Object> criarDoacao(@RequestBody Doacao doacao, @RequestParam List<Produto> itens) {
        try {
            Doacao novaDoacao = doacaoService.executarTransacao(doacao, itens);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaDoacao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }


    // Endpoint para atualizar uma doação existente
    @PutMapping("/{id}")
    public ResponseEntity<Doacao> atualizarDoacao(
            @PathVariable Long id,
            @RequestBody Doacao doacao) {
        try {
            doacao.setId(id);  // Garante que a atualização será para a doação com o ID correto
            Doacao doacaoAtualizada = doacaoService.salvarTransacao(doacao, produtoService.getAll()); // Passar itens se necessário
            return ResponseEntity.ok(doacaoAtualizada);
        } catch (RuntimeException e) {
            return status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint para deletar uma doação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoacao(@PathVariable Long id) {
        doacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
