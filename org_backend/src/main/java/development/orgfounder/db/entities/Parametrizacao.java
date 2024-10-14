package development.orgfounder.db.entities;


import jakarta.persistence.*;

@Entity
@Table(name="parametrizacao")
public class Parametrizacao {

    @Id //tirei o gerar chave automatico, ai eu passo a mandar o 1 quando tenho certeza que vou inserir e passo 0 no constructor vazio
    @Column(name="id_parametrizacao")
    private Long id;

    @Column(name="razao_social")
    private String razaoSocial;


    @Column(name="nome_fantasia")
    private String nomeFantasia;

    @Column(name="cnpj")
    private String cnpj;

    @Column(name="email")
    private String email;

    @Column(name="site")
    private String site;

    @Column(name="estado")
    private String estado;

    @Column(name="cidade")
    private String cidade;

    @Column(name="bairro")
    private String bairro;

    @Column(name="rua")
    private String rua;

    @Column(name="cep")
    private String cep;

    @Column(name="logotipo_grande")
    private String logotipoG;

    @Column(name="logotipo_pequeno")
    private String logotipoP;

    public Parametrizacao() {this(0L,"","","","","","","","","","","","");}

    public Parametrizacao(Long id, String razaoSocial, String nomeFantasia, String cnpj,
                          String email, String site, String estado, String cidade,
                          String bairro, String rua, String cep, String logotipoG,
                          String logotipoP) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.email = email;
        this.site = site;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.cep = cep;
        this.logotipoG = logotipoG;
        this.logotipoP = logotipoP;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogotipoG() {
        return logotipoG;
    }

    public void setLogotipoG(String logotipoG) {
        this.logotipoG = logotipoG;
    }

    public String getLogotipoP() {
        return logotipoP;
    }

    public void setLogotipoP(String logotipoP) {
        this.logotipoP = logotipoP;
    }
}
