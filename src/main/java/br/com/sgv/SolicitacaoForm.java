package br.com.sgv;

import br.com.sgv.model.Solicitacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitacaoForm {
    private long clienteId;
    private Solicitacao solicitacao;
}
