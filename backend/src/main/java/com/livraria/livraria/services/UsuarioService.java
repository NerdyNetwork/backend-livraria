package com.livraria.livraria.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.livraria.livraria.entities.Pedido;
import com.livraria.livraria.entities.Usuario;
import com.livraria.livraria.repositories.UsuarioRepository;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	public Usuario findById(Long id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Usuario> findAll() {
		List<Usuario> listUsuario = usuarioRepository.findAll();
		return listUsuario;
	}
	
	public Usuario findByEmail(String email) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		return usuario.orElseThrow(() -> new ResourceNotFoundException(email));
	}
	
	public List<Pedido> findPedidoByUsuario(Long id) {
		try {
			Optional<Usuario> usuario = usuarioRepository.findById(id);
			return usuario.get().getPedidos();
		} catch(NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public Usuario insert(Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

	public void deleteById(Long id) {
		try {
			usuarioRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Usuario updateUsuario(Long id, Usuario novoUsuario) {
		try {
			Usuario usuario = usuarioRepository.getReferenceById(id);
			updateUsuario(usuario, novoUsuario);
			return usuarioRepository.save(usuario);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateUsuario(Usuario usuario, Usuario novoUsuario) {
		usuario.setNome(novoUsuario.getNome());
		usuario.setEmail(novoUsuario.getEmail());
		usuario.setSenha(novoUsuario.getSenha());
		usuario.setTelefone(novoUsuario.getTelefone());
	}
}
