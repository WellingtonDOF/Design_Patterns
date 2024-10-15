package development.orgfounder.services.Strategies;

import development.orgfounder.db.entities.Produto;
import development.orgfounder.services.Interfaces.TransporteStrategy;

public class PesadoTransporteStrategy implements TransporteStrategy {
    @Override
    public String definirTransporte(Produto produto) {
        if (produto.getPeso() > 100) {
            return "Transportadora";
        }
        return null;
    }
}

