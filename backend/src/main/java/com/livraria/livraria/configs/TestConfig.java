package com.livraria.livraria.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.livraria.livraria.entities.Autor;
import com.livraria.livraria.entities.Categoria;
import com.livraria.livraria.entities.Fornecedor;
import com.livraria.livraria.entities.Livro;
import com.livraria.livraria.entities.Pedido;
import com.livraria.livraria.entities.Usuario;
import com.livraria.livraria.entities.enums.StatusDoPedido;
import com.livraria.livraria.entities.enums.TipoLivro;
import com.livraria.livraria.repositories.AutorRepository;
import com.livraria.livraria.repositories.CategoriaRepository;
import com.livraria.livraria.repositories.FornecedorRepository;
import com.livraria.livraria.repositories.LivroRepository;
import com.livraria.livraria.repositories.PedidoRepository;
import com.livraria.livraria.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired	
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Usuario user1 = new Usuario(null, "João Darwin", "joaodarwin.ip22@gmail.com", "88999950601", "ajajjouwoquw");
		Usuario user2 = new Usuario(null, "João Pedro", "jp@gmail.com", "8399999999", "sjsjjjj");
		
		usuarioRepository.saveAll(Arrays.asList(user1, user2));
		
		Pedido pedido1 = new Pedido(null, StatusDoPedido.APROVADO, user1);
		Pedido pedido2 = new Pedido(null, StatusDoPedido.CANCELADO, user1);
		Pedido pedido3 = new Pedido(null, StatusDoPedido.APROVADO, user2);
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));
		
		Autor autor1 = new Autor(null, "Dale Carnegie", "Biogradia do Dale Carnegie");
		Autor autor2 = new Autor(null, "Nicholas Boothman", "Biogradia do Nicholas Boothman");
		Autor autor3 = new Autor(null, "Robert Lewandowyski", "Biografia do Robert");
		
		autorRepository.saveAll(Arrays.asList(autor1, autor2, autor3));
		
		Fornecedor fornecedor1 = new Fornecedor(null, "Books Store", "14.123.123/0001-45");
		
		fornecedorRepository.save(fornecedor1);
		
		Categoria categoria1 = new Categoria(null, "Livros de Auto-Ajuda");
		Categoria categoria2 = new Categoria(null, "Revistas Bibliográficas");
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		
		Livro livro1 = new Livro(null, "Como fazer amigos e influenciar pessoas", "O guia clássico e definitivo para relacionar-se com as pessoas", "Companhia Editora Nacional", "9788504020267", 78787888, TipoLivro.LIVRO, autor1, fornecedor1);
		Livro livro2 = new Livro(null, "Como convencer alguém em 90 segundos", "Crie uma primeira impressão vendedora", "Universo dos Livros", "9788579303197", 79303197, TipoLivro.LIVRO, autor2, fornecedor1);
		Livro livro3 = new Livro(null, "Revista Genérica", "Descrição da revista genérica", "Universo Revistas", "78945455665", 122321, TipoLivro.REVISTA, autor3);
		
		livroRepository.saveAll(Arrays.asList(livro1, livro2, livro3));
		
		livro1.getCategorias().add(categoria1);
		livro2.getCategorias().add(categoria1);
		livro3.getCategorias().add(categoria2);
		livro3.getCategorias().add(categoria1);
		
		livroRepository.saveAll(Arrays.asList(livro1, livro2, livro3));
		
		pedido1.getLivros().addAll(Arrays.asList(livro1, livro2));
		pedido2.getLivros().add(livro3);
		pedido3.getLivros().addAll(Arrays.asList(livro1, livro2, livro3));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));
	}	
}