package br.com.sgv.repository;

import br.com.sgv.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository  extends CrudRepository<Cliente,Long> {
}
