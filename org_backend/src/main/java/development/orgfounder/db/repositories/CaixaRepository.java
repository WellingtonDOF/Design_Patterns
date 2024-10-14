package development.orgfounder.db.repositories;

import development.orgfounder.db.entities.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {
}
