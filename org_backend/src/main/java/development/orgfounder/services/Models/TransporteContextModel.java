package development.orgfounder.services.Models;

import development.orgfounder.db.entities.Produto;
import development.orgfounder.services.Interfaces.TransporteStrategy;

public class TransporteContextModel {
    private TransporteStrategy strategy;

    public void setStrategy(TransporteStrategy strategy) {
        this.strategy = strategy;
    }

    public String definirTransporte(Produto produto) {
        return strategy.definirTransporte(produto);
    }
}

