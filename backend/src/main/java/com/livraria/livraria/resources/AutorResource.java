package com.livraria.livraria.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/autor")
@CrossOrigin(origins = "*")
public class AutorResource {
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Autor> findById(@PathVariable Long id) {
		try {
			Autor autor = autorService.findById(id);
			return ResponseEntity.ok().body(autor);
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Autor>> findAll() {
		List<Autor> autores = autorService.findAll();
		return ResponseEntity.ok().body(autores);
	}
	
	@PostMapping
	public ResponseEntity<Autor> insert(@RequestBody Autor autor) {
		autor = autorService.insert(autor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).body(autor);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		autorService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<Autor> update(@PathVariable Long id, @RequestBody Autor novoAutor) {
		try {
			Autor autor = autorService.updateAutor(id, novoAutor);
			return ResponseEntity.ok().body(autor);
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
