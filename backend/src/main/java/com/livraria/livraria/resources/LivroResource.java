package com.livraria.livraria.resources;

import java.net.URI;
import java.util.List;
import java.util.Set;

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

import com.livraria.livraria.entities.Livro;
import com.livraria.livraria.entities.dtos.response.BookDTO;
import com.livraria.livraria.services.LivroService;
import com.livraria.livraria.services.exceptions.DatabaseException;
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
			System.out.println("[Erro no FindById: ]" + e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/findByName")
	public ResponseEntity<Livro> findByName(@RequestParam(value = "nome") String name) {
		try {
			Livro livro = livroService.findByName(name);
			return ResponseEntity.status(HttpStatus.OK).body(livro);
		} catch (ResourceNotFoundException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping(value = "/bestsellers")
	public ResponseEntity<Set<Livro>> bestSellersLivros() {
		try {
			Set<Livro> bestSellers = livroService.bestSellers();
			return ResponseEntity.ok().body(bestSellers);
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAll() {
		try {
			List<BookDTO> livros = livroService.findAllDtos();
			return ResponseEntity.ok().body(livros);
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Livro> insert(@RequestBody Livro livro) {
		try {
			livro = livroService.insert(livro);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
			return ResponseEntity.created(uri).body(livro);
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		try {
			livroService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody Livro novoLivro) {
		try {
			novoLivro = livroService.updateLivro(id, novoLivro);
			return ResponseEntity.ok().body(novoLivro);
		} catch (ResourceNotFoundException err) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (DatabaseException err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
