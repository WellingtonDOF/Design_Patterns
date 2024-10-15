package development.orgfounder.services.Interfaces;

import development.orgfounder.db.entities.Produto;

public interface TransporteStrategy {
    String definirTransporte(Produto produto);
}
