package development.orgfounder.db.entities;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="produto_entrega")
public class ProdutoEntrega {

    @EmbeddedId
    private ProdutoEntregaKey id;

    @Column(name="quantidade")
    private int quantidade;

    public ProdutoEntrega(){this(null,0);}
    public ProdutoEntrega(ProdutoEntregaKey id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public ProdutoEntregaKey getId() {
        return id;
    }

    public void setId(ProdutoEntregaKey id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void SetPeso()
    {
        ProdutoEntregaKey key = new ProdutoEntregaKey();
        double peso;
        peso=key.getId_produto().getPeso()*quantidade;
        key.getId_entrega().setPeso_total(key.getId_entrega().getPeso_total()+peso);
    }

}