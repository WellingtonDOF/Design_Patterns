package development.orgfounder.restcontrollers;

import development.orgfounder.db.entities.Doacao;
import development.orgfounder.db.entities.Produto;

import java.util.List;

public class DoacaoDTO {
    private Doacao doacao;
    private List<Produto> itens;

    public DoacaoDTO() {}

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }
}
