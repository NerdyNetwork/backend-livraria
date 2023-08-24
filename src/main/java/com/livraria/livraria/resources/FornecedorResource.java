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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.livraria.livraria.entities.Fornecedor;
import com.livraria.livraria.services.FornecedorService;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/fornecedores")
@CrossOrigin(origins = "*")
public class FornecedorResource {

	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
		try {
			Fornecedor fornecedor = fornecedorService.findById(id);
			return ResponseEntity.ok().body(fornecedor);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Fornecedor>> findAll() {
		try {
			List<Fornecedor> listFornecedor = fornecedorService.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(listFornecedor);
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Fornecedor> insert(@RequestBody Fornecedor fornecedor) {
		try {
			fornecedor = fornecedorService.insert(fornecedor);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fornecedor.getId()).toUri();
			return ResponseEntity.created(uri).body(fornecedor);
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		try {
			fornecedorService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (ResourceNotFoundException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
		try {
			Fornecedor fornecedorUpdate = fornecedorService.updateFornecedor(id, fornecedor);
			return ResponseEntity.ok().body(fornecedorUpdate);
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (ResourceNotFoundException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
