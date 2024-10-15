package development.orgfounder.db.repositories;

import development.orgfounder.db.entities.Donatario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonatarioRepository extends JpaRepository<Donatario, Long> {
    public Donatario findByCpf(String cpf);
}
