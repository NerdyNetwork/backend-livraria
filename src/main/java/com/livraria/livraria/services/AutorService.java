package com.livraria.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livraria.livraria.entities.Autor;
import com.livraria.livraria.repositories.AutorRepository;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	public Autor findById(Long id) {
		Optional<Autor> autor = autorRepository.findById(id);
		return autor.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Autor> findAll() {
		List<Autor> autores = autorRepository.findAll();
		if(autores.size() == 0) {
			throw new DatabaseException("Nenhum autor cadastrado");
		}
		return autores;
	}
	
	public Autor insert(Autor autor) {
		try {
			return autorRepository.save(autor);
		} catch (IllegalArgumentException err) {
			throw new DatabaseException("Erro ao inserir Autor");
		}
	}
	
	public void deleteById(Long id) {
		try {
			autorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Autor updateAutor(Long id, Autor novoAutor) {
		try {
			Autor autor = autorRepository.getReferenceById(id);
			updateAutor(autor, novoAutor);
			return autorRepository.save(autor);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (IllegalArgumentException err) {
			throw new DatabaseException("Erro no banco de dados");
		}
	}
	
	private void updateAutor(Autor autor, Autor novoAutor) {
		autor.setNome(novoAutor.getNome());
		autor.setBiografia(novoAutor.getBiografia());
	}
}
