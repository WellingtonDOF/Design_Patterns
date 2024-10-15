package development.orgfounder.services.Strategies;

import development.orgfounder.db.entities.Produto;
import development.orgfounder.services.Interfaces.TransporteStrategy;

public class LeveTransporteStrategy implements TransporteStrategy {
    @Override
    public String definirTransporte(Produto produto) {
        if (produto.getPeso() <= 10) {
            return "PAX";
        }
        return null;
    }
}

