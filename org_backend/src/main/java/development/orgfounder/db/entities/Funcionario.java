package development.orgfounder.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_funcionario")
    private Long id;
    @Column(name="nome")
    private String nome;
    @Column(name="cpf")
    private String cpf;
    @Column(name="telefone")
    private String telefone;
    @Column(name="endereco")
    private String endereco;
    @Column(name="email")
    private String email;
    @Column(name="sexo")
    private String sexo;
    @Column(name="estado")
    private String estado;
    @Column(name="cidade")
    private String cidade;
    @Column(name="bairro")
    private String bairro;
    @Column(name="cep")
    private String cep;
    @Column(name="dta_nascimento")
    private LocalDate dta_nascimento;
    @Column(name="dta_admissao")
    private LocalDate dta_admissao;
    @Column(name="dta_demissao")
    private LocalDate dta_demissao;

    public Funcionario(){this(0L,"","","","","","","","","","",null,null,null);}
    public Funcionario(Long id, String nome, String cpf, String telefone, String endereco, String email, String sexo, String estado, String cidade, String bairro, String cep, LocalDate dta_nascimento, LocalDate dta_admissao, LocalDate dta_demissao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.sexo = sexo;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.dta_nascimento = dta_nascimento;
        this.dta_admissao = dta_admissao;
        this.dta_demissao = dta_demissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDate getDta_nascimento() {
        return dta_nascimento;
    }

    public void setDta_nascimento(LocalDate dta_nascimento) {
        this.dta_nascimento = dta_nascimento;
    }

    public LocalDate getDta_admissao() {
        return dta_admissao;
    }

    public void setDta_admissao(LocalDate dta_admissao) {
        this.dta_admissao = dta_admissao;
    }

    public LocalDate getDta_demissao() {
        return dta_demissao;
    }

    public void setDta_demissao(LocalDate dta_demissao) {
        this.dta_demissao = dta_demissao;
    }
}
