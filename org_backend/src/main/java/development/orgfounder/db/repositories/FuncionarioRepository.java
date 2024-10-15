package development.orgfounder.db.repositories;

import development.orgfounder.db.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    public Funcionario findByCpf(String cpf);
}
