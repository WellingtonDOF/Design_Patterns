package development.orgfounder.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="caixa")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_caixa")
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_funcionario", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Funcionario id_funcionario;
    @Column(name="saldo_inicial")
    private Double saldo_inicial;
    @Column(name="saldo_final")
    private Double saldo_final;
    @Column(name="data")
    private LocalDate data;

    public Caixa() {this(0L,null,0.0,0.0,null);}

    public Caixa(Long id, Funcionario id_funcionario, Double saldo_inicial, Double saldo_final, LocalDate data) {
        this.id = id;
        this.id_funcionario = id_funcionario;
        this.saldo_inicial = saldo_inicial;
        this.saldo_final = saldo_final;
        this.data = data;
    }

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

    public Double getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(Double saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public Double getSaldo_final() {
        return saldo_final;
    }

    public void setSaldo_final(Double saldo_final) {
        this.saldo_final = saldo_final;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
