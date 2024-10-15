package development.orgfounder.db.repositories;

import development.orgfounder.db.entities.Estoque;
import development.orgfounder.db.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public Produto findByNome(String nome);//retorna o produto pesquisado

}
