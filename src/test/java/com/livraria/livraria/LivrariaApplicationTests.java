package com.livraria.livraria;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.livraria.livraria.entities.Livro;
import com.livraria.livraria.services.LivroService;

@SpringBootTest
class LivrariaApplicationTests {


	@Autowired
	private LivroService livroService;

	@Test
	void contextLoads() {
	}

	@Test
	public void findLivroById() {
		long id = 2;
		Livro livro = livroService.findById(id);
		Assertions.assertEquals(2, livro.getId());
	}

}
