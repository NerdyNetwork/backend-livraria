package com.livraria.livraria.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.livraria.livraria.entities.Usuario;
import com.livraria.livraria.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Usuario user1 = new Usuario(null, "João Darwin", "joaodarwin.ip22@gmail.com", "88999950601", "ajajjouwoquw");
		
		usuarioRepository.save(user1);
	}
	
}
