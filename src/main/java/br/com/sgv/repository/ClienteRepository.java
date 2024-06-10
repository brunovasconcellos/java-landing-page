package br.com.sgv.repository;

import br.com.sgv.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository  extends CrudRepository<Cliente,Long> {
    @Query("SELECT C.login, C.senha FROM Cliente C WHERE C.login = ?1 and C.senha = ?2")
    public String efetuarLogin(String login, String senha);
    public Cliente findByLogin(String login);

}
