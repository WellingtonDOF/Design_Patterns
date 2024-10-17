package development.orgfounder.services;

import development.orgfounder.db.entities.Doador;
import development.orgfounder.db.repositories.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoadorService {

    @Autowired
    private DoadorRepository doadorRepository;

    @Transactional
    public Doador save(Doador doador) {

        return doadorRepository.save(doador);
    }

    @Transactional(readOnly = true)
    public List<Doador> getAll() {
        return doadorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Doador getById(Long id) {

        return doadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado: " + id));
    }

    @Transactional
    public void delete(Long id) {
        // Exclui um doador pelo ID
        Doador doador = getById(id);
        doadorRepository.delete(doador);
    }
}
