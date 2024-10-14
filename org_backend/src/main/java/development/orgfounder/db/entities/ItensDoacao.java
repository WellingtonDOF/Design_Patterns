package development.orgfounder.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name="itens_doacao")
public class ItensDoacao {

    @EmbeddedId
    private ItensDoacaoKey id;

    @Column(name="quantidade")
    private int quantidade;

    public ItensDoacao() {this(null,0);}

    public ItensDoacao(ItensDoacaoKey id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public ItensDoacaoKey getId() {
        return id;
    }

    public void setId(ItensDoacaoKey id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
