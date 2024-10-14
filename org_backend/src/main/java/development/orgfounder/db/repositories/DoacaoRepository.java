package development.orgfounder.db.repositories;

import development.orgfounder.db.entities.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
}
