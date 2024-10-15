package development.orgfounder.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import development.orgfounder.db.entities.ControleAcesso;

public interface ControleAcessoRepository extends JpaRepository<ControleAcesso, Long> {

    public ControleAcesso findByLogin(String login);//Retorna as denuncias que o usuario tem
}
