package com.livraria.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.livraria.livraria.entities.Fornecedor;
import com.livraria.livraria.repositories.FornecedorRepository;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Fornecedor findById(Long id) {
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
		return fornecedor.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Fornecedor> findAll() {
		try {
			List<Fornecedor> listFornecedor = fornecedorRepository.findAll();
			return listFornecedor;
		} catch (Exception err) {
			throw new DatabaseException("Erro no banco de dados");
		}
	}
	
	public Fornecedor insert(Fornecedor fornecedor) {
		try {
			return fornecedorRepository.save(fornecedor);
		} catch (IllegalArgumentException | OptimisticLockingFailureException err) {
			throw new DatabaseException("Erro no banco de dados");
		}
	}
	
	public void deleteById(Long id) {
		try {
			fornecedorRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Fornecedor updateFornecedor(Long id, Fornecedor novoFornecedor) {
		try {
			Fornecedor fornecedor = fornecedorRepository.getReferenceById(id);
			updateFornecedor(fornecedor, novoFornecedor);
			return fornecedorRepository.save(fornecedor);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (IllegalArgumentException err) {
			throw new DatabaseException("Erro no banco de dados");
		}
	}
	
	private void updateFornecedor(Fornecedor fornecedor, Fornecedor novoFornecedor) {
		fornecedor.setNomeFantasia(novoFornecedor.getNomeFantasia());
		fornecedor.setCnpj(novoFornecedor.getCnpj());
	}
}
