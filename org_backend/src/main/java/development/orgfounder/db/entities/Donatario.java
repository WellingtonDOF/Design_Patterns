package development.orgfounder.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="donatario")
public class        Donatario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_donatario")
    private Long id;
    @Column(name="nome")
    private String nome;
    @Column(name="cpf")
    private String cpf;
    @Column(name="telefone")
    private String telefone;
    @Column(name="endereco")
    private String endereco;
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
    @Column(name="raca")
    private String raca;
    @Column(name="dta_nascimento")
    private LocalDate dta_nascimento;

    public Donatario(){this(0L,"","","","","","","","","","",null);}
    public Donatario(Long id, String nome, String cpf, String telefone, String endereco, String sexo, String estado, String cidade, String bairro, String cep, String raca, LocalDate dta_nascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.sexo = sexo;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.raca = raca;
        this.dta_nascimento = dta_nascimento;
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public LocalDate getDta_nascimento() {
        return dta_nascimento;
    }

    public void setDta_nascimento(LocalDate dta_nascimento) {
        this.dta_nascimento = dta_nascimento;
    }
}
