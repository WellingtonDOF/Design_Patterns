package development.orgfounder.db.repositories;

import development.orgfounder.db.entities.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    List<Estoque> findAll(); // busca o estoque disponivel
}
