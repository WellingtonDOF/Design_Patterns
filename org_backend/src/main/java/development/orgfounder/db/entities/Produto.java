package development.orgfounder.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name="produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produto")
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_estoque", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Estoque id_estoque;

    @Column(name="nome")
    private String nome;
    @Column(name="tipo")
    private String tipo;
    @Column(name="valor")
    private Double valor;

    public Produto() {this(0L,null,"","",0.0);}

    public Produto(Long id, Estoque id_estoque, String nome, String tipo, Double valor) {
        this.id = id;
        this.id_estoque = id_estoque;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estoque getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(Estoque id_estoque) {
        this.id_estoque = id_estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
