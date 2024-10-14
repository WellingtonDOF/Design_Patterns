package development.orgfounder.services;

import development.orgfounder.db.entities.ControleAcesso;
import development.orgfounder.db.entities.Parametrizacao;
import development.orgfounder.db.repositories.ControleAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControleAcessoService {

    @Autowired
    private ControleAcessoRepository repo;

    public ControleAcesso save(ControleAcesso controle){
        return repo.save(controle);
    }

    public ControleAcesso getById(Long id)
    {
        return repo.findById(id).get();
    }

    public ControleAcesso getByEmail(String email)
    {
        ControleAcesso controleAcesso = repo.findByLogin(email);

        if(controleAcesso==null)
            return null;
        return controleAcesso;
    }

    public boolean delete(Long id)
    {
        // vai tentar deletar caso de certo ele nao entra no catch e não retorna falso, apenas retorna true no final;
        // se ele entrar no catch é porque ele não conseguiu deletar e então retorna falso para poder tratar isso, dar alguma resposta.
        try{
            repo.deleteById(id);
        }catch(Exception e) {
            return false;
        }
        return true;
    }

    public List<ControleAcesso> getAll()
    {
        return repo.findAll();
    }


}
