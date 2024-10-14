package development.orgfounder.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="doacao")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_doacao")
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_caixa", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Caixa id_caixa;
    @ManyToOne
    @JoinColumn(name="id_doador", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Doador id_doador;
    @Column(name="categoria")
    private String categoria;
    @Column(name="status")
    private String status;
    @Column(name="data")
    private LocalDate data;
    @Column(name="valor")
    private Double valor;

    public Doacao(){this(0L,null,null,"","A",null,0.0);}
    public Doacao(Long id, Caixa id_caixa, Doador id_doador, String categoria, String status, LocalDate data, Double valor) {
        this.id = id;
        this.id_caixa = id_caixa;
        this.id_doador = id_doador;
        this.categoria = categoria;
        this.status = status;
        this.data = data;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Caixa getId_caixa() {
        return id_caixa;
    }

    public void setId_caixa(Caixa id_caixa) {
        this.id_caixa = id_caixa;
    }

    public Doador getId_doador() {
        return id_doador;
    }

    public void setId_doador(Doador id_doador) {
        this.id_doador = id_doador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
