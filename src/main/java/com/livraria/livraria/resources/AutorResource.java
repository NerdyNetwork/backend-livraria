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

import com.livraria.livraria.entities.Autor;
import com.livraria.livraria.services.AutorService;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/autores")
@CrossOrigin(origins = "${frontend_url}")
public class AutorResource {
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Autor> findById(@PathVariable Long id) {
		try {
			Autor autor = autorService.findById(id);
			return ResponseEntity.ok().body(autor);
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Autor>> findAll() {
		try {
			List<Autor> autores = autorService.findAll();
			return ResponseEntity.ok().body(autores);
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Autor> insert(@RequestBody Autor autor) {
		try {
			autor = autorService.insert(autor);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
			return ResponseEntity.created(uri).body(autor);
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		try {
			autorService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<Autor> update(@PathVariable Long id, @RequestBody Autor novoAutor) {
		try {
			Autor autor = autorService.updateAutor(id, novoAutor);
			return ResponseEntity.ok().body(autor);
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch(DatabaseException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
