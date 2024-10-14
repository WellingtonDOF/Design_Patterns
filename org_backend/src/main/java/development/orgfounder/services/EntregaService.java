package development.orgfounder.services;


import development.orgfounder.db.entities.ControleAcesso;
import development.orgfounder.db.entities.Entrega;
import development.orgfounder.db.repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository repo;
    public Entrega save(Entrega entrega){
        return repo.save(entrega);
    }

    public List<Entrega> getAll()
    {
        return repo.findAll();
    }
}

