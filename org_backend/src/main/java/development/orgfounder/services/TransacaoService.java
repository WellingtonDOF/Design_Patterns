package development.orgfounder.services;

import java.util.List;

public abstract class TransacaoService<T> {

    // Método Template que define o fluxo comum de salvamento, pode ser sobreposto se necessário
    public T salvarTransacao(T transacao, List<?> itens) {

        validarTransacao(transacao);
        processarItens(transacao, itens);
        return salvar(transacao);
    }

    // Método abstrato para validação que pode ser implementado em subclasses
    protected abstract void validarTransacao(T transacao);

    // Método abstrato para processamento dos itens
    protected abstract void processarItens(T transacao, List<?> itens);

    // Método abstrato para salvamento que as subclasses precisam implementar
    protected abstract T salvar(T transacao);
}
