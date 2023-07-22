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

import com.livraria.livraria.entities.Categoria;
import com.livraria.livraria.services.CategoriaService;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/categorias")
@CrossOrigin(origins = "*")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		try {
			Categoria categoria = categoriaService.findById(id);
			return ResponseEntity.ok().body(categoria);
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categorias = categoriaService.findAll();
		return ResponseEntity.ok().body(categorias);
	}
	
	@GetMapping(value = "/categoriasByName")
	public ResponseEntity<Categoria> findByNome(@RequestParam(name = "nome") String nome) {
		try {
			Categoria categoria = categoriaService.findByNome(nome);
			return ResponseEntity.ok().body(categoria);
		} catch(ResourceNotFoundException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Categoria> insert(@RequestBody Categoria categoria) {
		try {
			categoria = categoriaService.insert(categoria);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
			return ResponseEntity.created(uri).body(categoria);
		} catch (DatabaseException err) {
			return  ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		try {
			categoriaService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch(DatabaseException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (ResourceNotFoundException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> updareCategoria(@PathVariable Long id, @RequestBody Categoria novaCategoria) {
		try {
			Categoria categoria = categoriaService.updateCategoria(id, novaCategoria);
			return ResponseEntity.ok().body(categoria);
		} catch (ResourceNotFoundException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
	}
}
