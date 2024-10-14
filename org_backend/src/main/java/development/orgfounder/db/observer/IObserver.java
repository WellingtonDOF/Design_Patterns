package development.orgfounder.db.observer;

import development.orgfounder.db.entities.Estoque;

public interface IObserver {

    public void update(Estoque estoque);
}
