package br.com.sgv.repository;

import br.com.sgv.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository  extends CrudRepository<Cliente,Long> {
    @Query("SELECT c FROM Cliente c WHERE c.login = :username")
    Cliente findClienteByUsername(String username);
}
