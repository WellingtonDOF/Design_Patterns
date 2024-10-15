package development.orgfounder.services.Interfaces;

import development.orgfounder.db.entities.Estoque;

public interface IObserver {

    public void update(Estoque estoque);
}
