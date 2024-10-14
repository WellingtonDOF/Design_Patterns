package development.orgfounder.db.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ProdutoEntregaKey implements Serializable {
    @ManyToOne
    @JoinColumn(name="id_produto", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Produto id_produto;

    @ManyToOne
    @JoinColumn(name="id_entrega", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Entrega id_entrega;

    public ProdutoEntregaKey(){this(null,null);}

    public ProdutoEntregaKey(Produto id_produto, Entrega id_entrega) {
        this.id_produto = id_produto;
        this.id_entrega = id_entrega;
    }

    public Produto getId_produto() {
        return id_produto;
    }

    public void setId_produto(Produto id_produto) {
        this.id_produto = id_produto;
    }

    public Entrega getId_entrega() {
        return id_entrega;
    }

    public void setId_entrega(Entrega id_entrega) {
        this.id_entrega = id_entrega;
    }
}
