package development.orgfounder.services.Models;

import development.orgfounder.db.entities.ProdutoEntrega;
import development.orgfounder.services.Strategies.LeveTransporteStrategy;
import development.orgfounder.services.Strategies.ModeradoTransporteStrategy;
import development.orgfounder.services.Strategies.PesadoTransporteStrategy;

public class ProdutoEntregaModel {
    private ProdutoEntrega produtoEntrega;
    private TransporteContextModel transporteContext;

    public ProdutoEntregaModel(ProdutoEntrega produtoEntrega) {
        this.produtoEntrega = produtoEntrega;
        this.transporteContext = new TransporteContextModel();
    }

    public double calcularPesoTotal() {
        return produtoEntrega.getId().getId_produto().getPeso() * produtoEntrega.getQuantidade();
    }

    public String definirTransporte() {
        double pesoTotal = calcularPesoTotal();

        // Definir a estratégia com base no peso
        if (pesoTotal <= 10) {
            transporteContext.setStrategy(new LeveTransporteStrategy());
        } else if (pesoTotal > 10 && pesoTotal <= 100) {
            transporteContext.setStrategy(new ModeradoTransporteStrategy());
        } else {
            transporteContext.setStrategy(new PesadoTransporteStrategy());
        }

        // Retorna o meio de transporte sem modificar a entidade
        return transporteContext.definirTransporte(produtoEntrega.getId().getId_produto());
    }
}