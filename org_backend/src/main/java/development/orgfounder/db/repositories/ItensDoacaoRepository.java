package development.orgfounder.db.repositories;

import development.orgfounder.db.entities.ItensDoacao;
import development.orgfounder.db.entities.ItensDoacaoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensDoacaoRepository extends JpaRepository<ItensDoacao, ItensDoacaoKey> {
}
