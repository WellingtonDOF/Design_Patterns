package development.orgfounder.controllers;

import development.orgfounder.db.entities.Entrega;
import development.orgfounder.db.entities.ProdutoEntrega;
import development.orgfounder.services.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @PostMapping("criar-entrega")
    public ResponseEntity<Entrega> createEntrega(@RequestBody Entrega entrega, @RequestBody List<ProdutoEntrega> produtos) {
        try {
            Entrega savedEntrega = entregaService.executarTransacao(entrega, produtos);
            return new ResponseEntity<>(savedEntrega, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-entregas")
    public ResponseEntity<List<Entrega>> getAllEntregas() {
        List<Entrega> entregas = entregaService.getAll();
        return new ResponseEntity<>(entregas, HttpStatus.OK);
    }
}
