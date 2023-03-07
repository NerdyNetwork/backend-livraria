package com.livraria.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livraria.livraria.entities.Categoria;
import com.livraria.livraria.repositories.CategoriaRepository;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Categoria> findAll() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}
	
	public Categoria insert(Categoria categoria) {
		categoria = categoriaRepository.save(categoria);
		return categoria;
	}
	
	public void deleteById(Long id) {
		try {
			categoriaRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Categoria updateCategoria(Long id, Categoria novaCategoria) {
		try {
			Categoria categoria = categoriaRepository.getReferenceById(id);
			updateCategoria(categoria, novaCategoria);
			return categoriaRepository.save(categoria);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateCategoria(Categoria categoria, Categoria novaCategoria) {
		categoria.setNome(novaCategoria.getNome());
	}
}