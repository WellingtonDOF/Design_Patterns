package development.orgfounder.services;

import development.orgfounder.db.entities.Entrega;
import development.orgfounder.db.entities.Produto;
import development.orgfounder.db.entities.ProdutoEntrega;
import development.orgfounder.db.entities.ProdutoEntregaKey;
import development.orgfounder.db.repositories.EntregaRepository;
import development.orgfounder.db.repositories.ProdutoEntregaRepository;
import development.orgfounder.services.Models.ProdutoEntregaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntregaService extends TransacaoService<Entrega> {

    @Autowired
    private EntregaRepository repo;

    @Autowired
    private ProdutoEntregaRepository produtoEntregaRepository;

    @Autowired
    private ProdutoService produtoService;

    @Override
    protected void validarTransacao(Entrega entrega) {
        if (entrega.getId_donatario() == null) {
            throw new IllegalArgumentException("Donatário não pode ser nulo.");
        }
    }

    @Override
    protected void processarItens(Entrega entrega, List<?> produtos) {
        for (Object obj : produtos) {
            if (obj instanceof Produto) {
                ProdutoEntrega produtoEntrega = (ProdutoEntrega) obj;


                Long produtoId = produtoEntrega.getId().getId_produto().getId();


                Produto produto = produtoService.getById(produtoId);
                if (produto == null) {
                    throw new RuntimeException("Produto não encontrado: " + produtoId);
                }


                produtoEntrega.setId(new ProdutoEntregaKey(produto, entrega));


                ProdutoEntregaModel produtoEntregaModel = new ProdutoEntregaModel(produtoEntrega);


                String meioDeTransporte = produtoEntregaModel.definirTransporte();
                System.out.println("Meio de Transporte Definido: " + meioDeTransporte);

                // Salva a ProdutoEntrega
                produtoEntregaRepository.save(produtoEntrega);
            } else {
                throw new IllegalArgumentException("Item da lista não é do tipo ProdutoEntrega.");
            }
        }
    }

    @Override
    protected Entrega salvar(Entrega entrega) {
        return repo.save(entrega);
    }

    @Transactional
    public Entrega executarTransacao(Entrega entrega, List<ProdutoEntrega> produtos) {
        validarTransacao(entrega);
        processarItens(entrega, produtos);
        return salvar(entrega);
    }

    @Transactional(readOnly = true)
    public List<Entrega> getAll() {
        return repo.findAll();
    }
}
