package br.com.bgrsys.cadastro_cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bgrsys.cadastro_cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query(value = "select c from Cliente c where upper(trim(c.nome)) like %?1%")
	List<Cliente> buscarPorNome(String name);

}
