package com.livraria.livraria.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.livraria.livraria.entities.Pedido;
import com.livraria.livraria.entities.dtos.response.PedidoUpdateDTO;
import com.livraria.livraria.services.PedidoService;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/pedidos")
@CrossOrigin(origins = "${frontend_url}")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		try {
			Pedido pedido = pedidoService.findById(id);
			return ResponseEntity.ok().body(pedido);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll() {
		List<Pedido> listPedido = pedidoService.findAll();
		if(listPedido.size() != 0) {
			return ResponseEntity.ok().body(listPedido);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Pedido> insert(@RequestBody Pedido pedido, @RequestParam Long usuario_id) {
		try {
			pedido = pedidoService.insert(pedido, usuario_id);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
			return ResponseEntity.created(uri).body(pedido);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		try {
			pedidoService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody PedidoUpdateDTO pedido) {
		try {
			Pedido pedidoUpdate = pedidoService.updatePedido(id, pedido);
			return ResponseEntity.ok().body(pedidoUpdate);
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}