package development.orgfounder.services;

import development.orgfounder.db.entities.ControleAcesso;
import development.orgfounder.db.entities.Donatario;
import development.orgfounder.db.repositories.DonatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonatarioService {

    @Autowired
    private DonatarioRepository repo;
    public Donatario getByCPF(String cpf)
    {
        Donatario donatario = repo.findByCpf(cpf);
        if(donatario==null)
            return null;
        return donatario;
    }

    public Donatario getById(Long id)
    {
        return repo.findById(id).get();
    }

}
