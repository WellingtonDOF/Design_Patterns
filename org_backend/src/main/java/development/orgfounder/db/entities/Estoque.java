package development.orgfounder.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estoque")
    private Long id;

    @Column(name="quantidade")
    private int quantidade;

    @Column(name="validade")
    private LocalDate validade;

    public Estoque() {this(0L,0,null);}
    public Estoque(Long id, int quantidade, LocalDate validade) {
        this.id = id;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }
}
