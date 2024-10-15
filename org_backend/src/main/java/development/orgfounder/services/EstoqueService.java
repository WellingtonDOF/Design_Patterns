package development.orgfounder.services;

import development.orgfounder.db.entities.Estoque;
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
}
