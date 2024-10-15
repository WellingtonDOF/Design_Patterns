package development.orgfounder.services;

import development.orgfounder.db.entities.Parametrizacao;
import development.orgfounder.db.repositories.ParametrizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ParametrizacaoService {

    @Autowired
    private ParametrizacaoRepository repo;

    @Autowired
    private ResourceLoader resourceLoader;



    public Parametrizacao getById(Long id)
    {
        //Verifica o ID se ele existir retorna o usuario, caso não exista retorna um usuario vazio
        Parametrizacao parametrizacao = repo.findById(id).orElse(new Parametrizacao());
        return parametrizacao;
    }

    public Parametrizacao save(Parametrizacao parametrizacao) {
        return repo.save(parametrizacao);
    }

    public List<Parametrizacao> getAll() {
        return repo.findAll();
    }

    public void delete(Long id)
    {
        //sempre vai dar certo porque estou setando o id sempre pra 1, então se eu chamei o deletar é pq alguem já clicou pra cadastrar um novo.
        repo.deleteById(id);
    }

    public String getStaticPath() throws IOException {
        String staticPath = null;
        staticPath = resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();
        return staticPath;
    }

    public String tirarEspaco(String texto) {
        return texto.replaceAll("\\s+", "");
    }
}
