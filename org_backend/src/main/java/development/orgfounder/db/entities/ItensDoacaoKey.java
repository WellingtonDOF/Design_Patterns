package development.orgfounder.db.entities;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ItensDoacaoKey implements Serializable {

    @ManyToOne
    @JoinColumn(name="id_produto", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Produto id_produto;
    @ManyToOne
    @JoinColumn(name="id_doacao", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Doacao id_doacao;

    public ItensDoacaoKey() {this(null,null);}

    public ItensDoacaoKey(Produto id_produto, Doacao id_doacao) {
        this.id_produto = id_produto;
        this.id_doacao = id_doacao;
    }

    public Produto getId_produto() {
        return id_produto;
    }

    public void setId_produto(Produto id_produto) {
        this.id_produto = id_produto;
    }

    public Doacao getId_doacao() {
        return id_doacao;
    }

    public void setId_doacao(Doacao id_doacao) {
        this.id_doacao = id_doacao;
    }
}
