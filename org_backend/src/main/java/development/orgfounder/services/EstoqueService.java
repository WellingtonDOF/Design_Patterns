package development.orgfounder.services;

import development.orgfounder.db.entities.ControleAcesso;
import development.orgfounder.db.entities.Estoque;
import development.orgfounder.db.entities.Produto;
import development.orgfounder.db.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Estoque> obterTodosOsEstoques() {
        return estoqueRepository.findAll();
    }

    public Estoque save(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque getById(Long id) {
        // Verifica se o estoque existe no banco de dados
        if (estoqueRepository.existsById(id)) {
            return estoqueRepository.findById(id).get(); // Método do JpaRepository que retorna um Optional
        }
        return null; // Retorna null se não encontrado
    }

}
