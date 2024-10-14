package development.orgfounder.db.repositories;

import development.orgfounder.db.entities.ProdutoEntrega;
import development.orgfounder.db.entities.ProdutoEntregaKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoEntregaRepository extends JpaRepository<ProdutoEntrega, ProdutoEntregaKey> {
}
