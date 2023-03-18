package com.livraria.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livraria.livraria.entities.Categoria;
import com.livraria.livraria.entities.Livro;
import com.livraria.livraria.repositories.LivroRepository;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public Livro findById(Long id) {
		Optional<Livro> livro = livroRepository.findById(id);
		return livro.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Livro> findAll() {
		List<Livro> livros = livroRepository.findAll();
		return livros;
	}
	
	public Livro insert(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public void deleteById(Long id) {
		try {
			livroRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Livro updateLivro(Long id, Livro novoLivro) {
		try {
			Livro livro = livroRepository.getReferenceById(id);
			updateLivro(livro, novoLivro);
			return livroRepository.save(livro);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public Livro updateQuantiVendasLivro(Long id) {
		try {
			Livro livro = livroRepository.getReferenceById(id);
			livro.atualizarQuantidadeCompras();
			return livroRepository.save(livro);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateLivro(Livro livro, Livro novoLivro) {
		livro.setNome(novoLivro.getNome());
		livro.setDescricao(novoLivro.getDescricao());
		livro.setEditora(novoLivro.getEditora());
		livro.setIsbn(novoLivro.getIsbn());
		livro.setCodigoDeBarras(novoLivro.getCodigoDeBarras());
		livro.setAutor(novoLivro.getAutor());
		livro.setFornecedor(novoLivro.getFornecedor());
		livro.setQuantidadeCompras(novoLivro.getQuantidadeCompras());
		if(novoLivro.getCategorias().size() != 0) {
			livro.getCategorias().clear();
			for(Categoria categoria : novoLivro.getCategorias()) {
				livro.getCategorias().add(categoria);
			}
		}
	}
}
