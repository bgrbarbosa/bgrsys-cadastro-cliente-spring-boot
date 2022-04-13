package br.com.bgrsys.cadastro_cliente.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bgrsys.cadastro_cliente.model.Cliente;
import br.com.bgrsys.cadastro_cliente.repository.ClienteRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired /* IC/CD ou CDI - Injeção de dependencia */
	private ClienteRepository clienteRepository;
	
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    

    @PostMapping(value = "salvar") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) { /* Recebe os dados para salvar */

		Cliente cli = clienteRepository.save(cliente);
		return new ResponseEntity<Cliente>(cli, HttpStatus.CREATED);

	}
    
	@GetMapping(value = "listarclientes") /* Nosso primeiro método de API */
	@ResponseBody /* Retorna os dados par ao corpo da resposta */
	public ResponseEntity<List<Cliente>> listarCliente() {

		List<Cliente> clientes = clienteRepository.findAll();/* executa a consulta no banco de dados */
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);/* Retorna a lista em JSON */

	}
	
	@PutMapping(value = "update") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Cliente cliente) { /* Recebe os dados para salvar */
		
		if (cliente.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
		}

		Cliente cli = clienteRepository.saveAndFlush(cliente);
		return new ResponseEntity<Cliente>(cli, HttpStatus.OK);

	}
	
	@DeleteMapping(value = "delete") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<String> delete(@RequestParam Long id) { /* Recebe os dados para delete */

		clienteRepository.deleteById(id);
		return new ResponseEntity<String>("Cliente deletado com sucesso", HttpStatus.OK);

	}
	
	@GetMapping(value = "buscarporcodigo") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<Cliente> buscarclienteid(@RequestParam(name = "id") Long id) { /* Recebe os dados para consultar */

		Cliente cliente = clienteRepository.findById(id).get();
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

	}
	
	@GetMapping(value = "buscarpornome") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<List<Cliente>> buscarPorNome(@RequestParam(name = "nome") String nome) { /* Recebe os dados para consultar */

		List<Cliente> cliente = clienteRepository.buscarPorNome(nome.trim().toUpperCase());
		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);

	}
}
