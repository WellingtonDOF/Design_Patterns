package development.orgfounder.db.repositories;

import development.orgfounder.db.entities.ControleAcesso;
import development.orgfounder.db.entities.Estoque;
import development.orgfounder.db.entities.Funcionario;
import development.orgfounder.db.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    List<Estoque> findAll(); // busca o estoque disponivel

}
