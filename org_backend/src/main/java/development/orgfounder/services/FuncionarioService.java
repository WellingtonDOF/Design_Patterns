package development.orgfounder.services;

import development.orgfounder.db.entities.ControleAcesso;
import development.orgfounder.db.entities.Donatario;
import development.orgfounder.db.entities.Funcionario;
import development.orgfounder.db.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repo;
    public Funcionario getByCPF(String cpf)
    {
        Funcionario funcionario = repo.findByCpf(cpf);

        System.out.println(funcionario);
        if(funcionario==null)
            return null;
        return funcionario;
    }

    public Funcionario getById(Long id)
    {
        return repo.findById(id).get();
    }

}
