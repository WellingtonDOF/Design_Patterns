package development.orgfounder.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name="controle_acesso")
public class ControleAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_controle_acesso")
    private Long id;

    @Column(name="login")
    private String login;

    @Column(name="senha")
    private String senha;

    @Column(name="nivel")
    private String nivel;//pode ser que seja char, se não der mudar para integer no banco

    @Column(name="nome")
    private String nome;

    @Column(name="status")
    private String status;

    @Column(name="notify")
    private boolean notify;


    public ControleAcesso() {this(0L, "", "", "0","","A", false);}

    public ControleAcesso(Long id, String login, String senha, String nivel, String nome, String status, boolean notify) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nivel = nivel;
        this.nome = nome;
        this.status = status;
        this.notify = notify;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
