package development.orgfounder.services;

import development.orgfounder.db.entities.*;
import development.orgfounder.db.repositories.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoacaoService extends TransacaoService<Doacao> {

    @Autowired
    private DoacaoRepository repo;

    @Autowired
    private DoadorService doadorService; // Serviço para buscar o doador

    @Override
    protected void validarTransacao(Doacao doacao) {
        if (doacao.getId_doador() == null) {
            throw new RuntimeException("Doador não pode ser nulo.");
        }
        if (doacao.getValor() == null || doacao.getValor() <= 0) {
            throw new RuntimeException("Valor da doação deve ser positivo.");
        }
    }

    @Override
    protected void processarItens(Doacao doacao, List<?> itens) {
        // Verifica se a lista de itens é do tipo correto
        if (!(itens instanceof Produto)) {
            throw new RuntimeException("Itens devem ser do tipo List<ItensDoacao>.");
        }

        List<Produto> itensDoacao = (List<Produto>) itens;

        // Processa cada item
        for (Produto item : itensDoacao) {
            // Validação da quantidade
            if (item.getId_estoque().getQuantidade() <= 0) {
                throw new RuntimeException("A quantidade deve ser maior que zero para o item: " + item.getId());
            }
            System.out.println("Processando item: " + item.getId() + " com quantidade: " + item.getId_estoque().getQuantidade());
        }
    }

    @Override
    @Transactional
    protected Doacao salvar(Doacao doacao) {
        return repo.save(doacao);
    }

    @Transactional(readOnly = true)
    public List<Doacao> getAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Doacao getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada: " + id));
    }

    @Transactional
    public void delete(Long id) {
        Doacao doacao = getById(id);
        repo.delete(doacao);
    }

    @Transactional
    public Doacao executarTransacao(Doacao doacao, List<Produto> itens) {
        validarTransacao(doacao);
        processarItens(doacao, itens);
        return salvar(doacao);
    }
}
