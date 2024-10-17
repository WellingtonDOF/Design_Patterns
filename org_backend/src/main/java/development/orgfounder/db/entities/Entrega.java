package development.orgfounder.db.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario id_funcionario;

    @ManyToOne
    @JoinColumn(name = "id_donatario", nullable = false)
    private Donatario id_donatario;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "status")
    private String status;

    @Column(name = "peso_total")
    private Double peso_total;



    // Construtores, Getters e Setters

    public Entrega() {
        this(0L, null, null, "", null, "A", 0.0);
    }

    public Entrega(Long id, Funcionario id_funcionario, Donatario id_donatario, String tipo, LocalDate data, String status, Double peso_total) {
        this.id = id;
        this.id_funcionario = id_funcionario;
        this.id_donatario = id_donatario;
        this.tipo = tipo;
        this.data = data;
        this.status = status;
        this.peso_total = peso_total;
    }

    // Getters e Setters omitidos para brevidade

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Funcionario id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public Donatario getId_donatario() {
        return id_donatario;
    }

    public void setId_donatario(Donatario id_donatario) {
        this.id_donatario = id_donatario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPeso_total() {
        return peso_total;
    }

    public void setPeso_total(Double peso_total) {
        this.peso_total = peso_total;
    }

    /*
    public List<ProdutoEntregaKey> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoEntregaKey> produtos) {
        this.produtos = produtos;
    }
    */

}
