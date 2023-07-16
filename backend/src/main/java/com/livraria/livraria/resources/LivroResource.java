package com.livraria.livraria.resources;

import java.net.URI;
import java.util.List;
import java.util.Set;

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

import com.livraria.livraria.entities.Livro;
import com.livraria.livraria.services.LivroService;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/livros")
@CrossOrigin(origins = "*")
public class LivroResource {
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id) {
		try {
			Livro livro = livroService.findById(id);
			return ResponseEntity.ok().body(livro);
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value = "/bestsellers")
	public ResponseEntity<Set<Livro>> bestSellersLivros() {
		Set<Livro> bestSellers = livroService.bestSellers();
		return ResponseEntity.ok().body(bestSellers);
	}
	
	@GetMapping
	public ResponseEntity<List<Livro>> findAll() {
		List<Livro> livros = livroService.findAll();
		return ResponseEntity.ok().body(livros);
	}
	
	@PostMapping
	public ResponseEntity<Livro> insert(@RequestBody Livro livro) {
		livro = livroService.insert(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).body(livro);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		livroService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody Livro novoLivro) {
		novoLivro = livroService.updateLivro(id, novoLivro);
		return ResponseEntity.ok().body(novoLivro);
	}
}
