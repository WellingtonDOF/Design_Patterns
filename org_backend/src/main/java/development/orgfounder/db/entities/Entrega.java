package development.orgfounder.db.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_entrega")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_funcionario", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Funcionario id_funcionario;

    @ManyToOne
    @JoinColumn(name="id_donatario", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Donatario id_donatario;
    //Objeto tipo orgao

    @Column(name="tipo")
    private String tipo;
    @Column(name="data")
    private LocalDate data;
    @Column(name="status")
    private String status;

    @Column(name="peso_total")
    private Double peso_total;

    public Entrega(){this(0L,null,null,"",null,"A",0.0);}
    public Entrega(Long id, Funcionario id_funcionario, Donatario id_donatario, String tipo, LocalDate data, String status,Double peso_total) {
        this.id = id;
        this.id_funcionario = id_funcionario;
        this.id_donatario = id_donatario;
        this.tipo = tipo;
        this.data = data;
        this.status = status;
        this.peso_total = peso_total;
    }

    public Double getPeso_total() {
        return peso_total;
    }

    public void setPeso_total(Double peso_total) {
        this.peso_total = peso_total;
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
}
