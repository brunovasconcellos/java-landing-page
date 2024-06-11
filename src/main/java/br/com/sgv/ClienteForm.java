package br.com.sgv;

import br.com.sgv.model.Cliente;
import br.com.sgv.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteForm {
    private Cliente cliente;
    private Usuario usuario;
}
