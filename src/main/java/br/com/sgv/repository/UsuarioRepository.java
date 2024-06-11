package br.com.sgv.repository;

import br.com.sgv.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository  extends CrudRepository<Usuario,Long> {
    @Query("SELECT C.login, C.senha FROM Cliente C WHERE C.login = ?1 and C.senha = ?2")
    public String efetuarLogin(String login, String senha);
    public Usuario findByLogin(String login);

}
