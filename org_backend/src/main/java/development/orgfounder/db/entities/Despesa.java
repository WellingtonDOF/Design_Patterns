package development.orgfounder.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_despesa")
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_caixa", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Caixa id_caixa;
    @ManyToOne
    @JoinColumn(name="id_funcionario", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Funcionario id_funcionario;

    @Column(name="valor")
    private Double valor;

    @Column(name="descricao")
    private String descricao;

    @Column(name="valor_pagamento")
    private Double valor_pagamento;

    @Column(name="dta_pagamento")
    private LocalDate dta_pagamento;

    @Column(name="dta_vencimento")
    private LocalDate dta_vencimento;

    public Despesa(){this(0L,null,null,0.0,"",0.0,null,null);}
    public Despesa(Long id, Caixa id_caixa, Funcionario id_funcionario, Double valor, String descricao, Double valor_pagamento, LocalDate dta_pagamento, LocalDate dta_vencimento) {
        this.id = id;
        this.id_caixa = id_caixa;
        this.id_funcionario = id_funcionario;
        this.valor = valor;
        this.descricao = descricao;
        this.valor_pagamento = valor_pagamento;
        this.dta_pagamento = dta_pagamento;
        this.dta_vencimento = dta_vencimento;
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

    public Funcionario getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Funcionario id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor_pagamento() {
        return valor_pagamento;
    }

    public void setValor_pagamento(Double valor_pagamento) {
        this.valor_pagamento = valor_pagamento;
    }

    public LocalDate getDta_pagamento() {
        return dta_pagamento;
    }

    public void setDta_pagamento(LocalDate dta_pagamento) {
        this.dta_pagamento = dta_pagamento;
    }

    public LocalDate getDta_vencimento() {
        return dta_vencimento;
    }

    public void setDta_vencimento(LocalDate dta_vencimento) {
        this.dta_vencimento = dta_vencimento;
    }
}
