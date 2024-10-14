package development.orgfounder.db.repositories;

import development.orgfounder.db.entities.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<Doador, Long> {
}
