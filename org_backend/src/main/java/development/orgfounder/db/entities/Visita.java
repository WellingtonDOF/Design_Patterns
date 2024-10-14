package development.orgfounder.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="visita")
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_visita")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_funcionario", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Funcionario id_funcionario;

    @ManyToOne
    @JoinColumn(name="id_donatario", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Donatario id_donatario;

    @Column(name="endereco")
    private String endereco;

    @Column(name="descricao")
    private String descricacao;

    @Column(name="dta_visita")
    private LocalDate dta_visita;

    public Visita(){this(0L,null,null,"","",null);}

    public Visita(Long id, Funcionario id_funcionario, Donatario id_donatario, String endereco, String descricacao, LocalDate dta_visita) {
        this.id = id;
        this.id_funcionario = id_funcionario;
        this.id_donatario = id_donatario;
        this.endereco = endereco;
        this.descricacao = descricacao;
        this.dta_visita = dta_visita;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricacao() {
        return descricacao;
    }

    public void setDescricacao(String descricacao) {
        this.descricacao = descricacao;
    }

    public LocalDate getDta_visita() {
        return dta_visita;
    }

    public void setDta_visita(LocalDate dta_visita) {
        this.dta_visita = dta_visita;
    }
}
