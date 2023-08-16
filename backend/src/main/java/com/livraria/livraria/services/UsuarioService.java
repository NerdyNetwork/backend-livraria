package com.livraria.livraria.services;

import java.util.ArrayList;
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
import com.livraria.livraria.entities.dtos.response.UsuarioDTO;
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

	public UsuarioDTO findById(Long id) {
		try {
			Optional<Usuario> obj = usuarioRepository.findById(id);
			UsuarioDTO userDto = new UsuarioDTO(obj.get().getId(), obj.get().getNome(), obj.get().getEmail(), obj.get().getTelefone());
			return userDto;
		} catch(NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public List<UsuarioDTO> findAll() {
		List<Usuario> listUsuario = usuarioRepository.findAll();
		List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();
		for(Usuario user : listUsuario) {
			UsuarioDTO userDto = new UsuarioDTO(user.getId(), user.getNome(), user.getEmail(), user.getTelefone());
			listUsuarioDTO.add(userDto);
		}
		return listUsuarioDTO;
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

	public UsuarioDTO insert(Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone());
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

	public UsuarioDTO updateUsuario(Long id, Usuario novoUsuario) {
		try {
			Usuario usuario = usuarioRepository.getReferenceById(id);
			updateUsuario(usuario, novoUsuario);
			UsuarioDTO userDto = new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone());	
			usuarioRepository.save(usuario);
			return userDto;
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
