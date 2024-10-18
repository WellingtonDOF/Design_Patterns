package development.orgfounder.services;

import java.util.List;

public abstract class TransacaoService<T> {


    public T salvarTransacao(T transacao, List<?> itens) {

        validarTransacao(transacao);
        processarItens(transacao, itens);
        return salvar(transacao);
    }


    protected abstract void validarTransacao(T transacao);


    protected abstract void processarItens(T transacao, List<?> itens);


    protected abstract T salvar(T transacao);
}
