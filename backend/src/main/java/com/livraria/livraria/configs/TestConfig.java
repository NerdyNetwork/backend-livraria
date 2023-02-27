package com.livraria.livraria.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.livraria.livraria.entities.Book;
import com.livraria.livraria.repository.BookRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Book bk1 = new Book(null, "Livro da minha vida");
		Book bk2 = new Book(null, "Livro de minha morada kkk");
		
		bookRepository.saveAll(Arrays.asList(bk1, bk2));
	}
	
}
