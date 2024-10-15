package development.orgfounder.db.entities;

import development.orgfounder.services.Interfaces.IObserver;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estoque")
    private Long id;

    @Column(name="quantidade")
    private int quantidade;

    @Column(name="validade")
    private LocalDate validade;

    @Transient
    private List<IObserver> observers = new ArrayList<>(); //coloquei como transient para o JPA ignorar..


    public Estoque() {this(0L,0,null);}
    public Estoque(Long id, int quantidade, LocalDate validade) {
        this.id = id;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        if (quantidade < 5) { // Supondo que 5 seja o limite mínimo
            notifyObservers();
        }
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public void attach(IObserver observer) {
        observers.add(observer);
    }

    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(this); //ta puxando o estoque dessa classe, para realizar o update
        }
    }
}
