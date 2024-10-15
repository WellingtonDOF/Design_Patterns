package development.orgfounder.db.entities;

import development.orgfounder.db.observer.IObserver;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="controle_acesso")
public class ControleAcesso implements IObserver {

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

    @Override
    public void update(Estoque estoque) {

        int limiteMinimo=5;

        if(estoque.getQuantidade()<limiteMinimo && this.isNotify())
        {
            System.out.println("Atenção, o estoque do produto " + estoque.getId() + " está abaixo do limite mínimo! Quantidade atual: " + estoque.getQuantidade());
        }
    }

    public void cadastrarParaNotificacoes(List<Estoque> estoques) {
        for (Estoque estoque : estoques) {
            estoque.attach(this); // Para cadastrar em todos os estoques
        }
    }
}
