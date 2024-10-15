package development.orgfounder.services;

import development.orgfounder.db.entities.Entrega;
import development.orgfounder.db.entities.Produto;
import development.orgfounder.db.entities.ProdutoEntrega;
import development.orgfounder.db.entities.ProdutoEntregaKey;
import development.orgfounder.db.repositories.EntregaRepository;
import development.orgfounder.services.Models.ProdutoEntregaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository repo;

    @Autowired
    private ProdutoService produtoService; // Serviço para buscar o produto

    public Entrega save(Entrega entrega, List<ProdutoEntrega> produtos) {
        // Itera sobre cada ProdutoEntrega e processa
        for (ProdutoEntrega produtoEntrega : produtos) {
            // Obtém o produto correspondente usando a chave do ProdutoEntrega
            Long produtoId = produtoEntrega.getId().getId_produto().getId();
            Produto produto = produtoService.getById(produtoId);
            if (produto == null) {
                throw new RuntimeException("Produto não encontrado: " + produtoId);
            }

            // Atualiza a chave de ProdutoEntrega com a entrega atual
            produtoEntrega.setId(new ProdutoEntregaKey(produto, entrega));

            // Cria a model de ProdutoEntrega para calcular o peso total e definir o transporte
            ProdutoEntregaModel produtoEntregaModel = new ProdutoEntregaModel(produtoEntrega);

            // Calcula o transporte e aplica a lógica antes de salvar a entrega
            String meioDeTransporte = produtoEntregaModel.definirTransporte();
            System.out.println("Meio de Transporte Definido: " + meioDeTransporte);

            // Salva a ProdutoEntrega
            // Aqui você deve implementar o salvamento no repositório de ProdutoEntrega
            // Exemplo: produtoEntregaRepository.save(produtoEntrega);
        }

        // Salva a entrega com as informações de ProdutoEntrega associadas
        return repo.save(entrega);
    }

    public List<Entrega> getAll() {
        return repo.findAll();
    }
}
