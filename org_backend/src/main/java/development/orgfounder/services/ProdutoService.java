package development.orgfounder.services;

import development.orgfounder.db.entities.Produto;
import development.orgfounder.db.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Método para salvar um produto
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Método para buscar um produto pelo ID
    public Produto getById(Long id) {
        // Verifica se o produto existe no banco de dados
        if (produtoRepository.existsById(id)) {
            return produtoRepository.findById(id).get(); // Método do JpaRepository que retorna um Optional
        }
        return null; // Retorna null se não encontrado
    }

    // Método para listar todos os produtos
    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    // Método para excluir um produto
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
