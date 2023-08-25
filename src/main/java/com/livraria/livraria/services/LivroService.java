package com.livraria.livraria.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livraria.livraria.entities.Categoria;
import com.livraria.livraria.entities.Livro;
import com.livraria.livraria.entities.dtos.response.BookDTO;
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
	
	public List<BookDTO> findAll() {
		try {
			List<Livro> books = livroRepository.findAll();
			List<BookDTO> booksDtos = new ArrayList<>();
			for(Livro book : books) {
				BookDTO bookDto = new BookDTO(book.getId(), book.getNome(), book.getQuantidadeCompras());
				booksDtos.add(bookDto);
			}
			return booksDtos;
		} catch (Exception err) {
			throw new DatabaseException("Erro no banco de dados");
		}
	}

	public Livro findByName(String name) {
		try {
			Livro livro = livroRepository.findByNome(name);
			return livro;
		} catch (IllegalArgumentException err) {
			throw new ResourceNotFoundException(name);
		}
	}
	
	public Set<BookDTO> bestSellers() throws DatabaseException {
		try {
			List<BookDTO> allBooks = findAll();
			Set<BookDTO> bestSellersBooks = new LinkedHashSet<>();

			organizeBestSellersBooks(allBooks, bestSellersBooks);

			return bestSellersBooks;
		} catch (Exception err) {
			throw new DatabaseException(err.getMessage());
		}
	}

	private void organizeBestSellersBooks(List<BookDTO> allBooks, Set<BookDTO> bestSellersBooks) {
		// Ordena pelo atributo quantidade de compras
		allBooks.sort(Comparator.comparing(BookDTO::amount).reversed());
		
		// Verifica se existe mais do que 20 livros, caso sim puxa os 20 mais vendidos, caso não puxa a quantidade que tem
		for(int i = 0; i < ((allBooks.size() < 20) ? allBooks.size() : 20); i++) {
			BookDTO livroSelecionado = allBooks.get(i);
			bestSellersBooks.add(livroSelecionado);
		}
	}
	
	public Livro insert(Livro livro) {
		try {
			return livroRepository.save(livro);
		} catch (IllegalArgumentException err) {
			throw new DatabaseException("Erro no banco de dados");
		}
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
		} catch (IllegalArgumentException err) {
			throw new DatabaseException("Erro no banco de dados");
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
