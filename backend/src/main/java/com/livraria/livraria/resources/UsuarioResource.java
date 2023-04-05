package com.livraria.livraria.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.livraria.livraria.entities.Pedido;
import com.livraria.livraria.entities.Usuario;
import com.livraria.livraria.entities.dtos.UsuarioDTO;
import com.livraria.livraria.services.UsuarioService;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/user")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		try {
			UsuarioDTO usuario = usuarioService.findById(id);
			return ResponseEntity.ok().body(usuario);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> list = usuarioService.findAll();
		if(list.size() != 0) {
			return ResponseEntity.ok().body(list);
		} 
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/login")
	public ResponseEntity<Boolean> login(@RequestParam String email, @RequestParam String senha) {
		Usuario usuario = usuarioService.findByEmail(email);
//		Condição para caso o email não seja válido
		if(!usuario.getEmail().equals(email)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		
		Boolean valid = encoder.matches(senha, usuario.getSenha());
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		
		return ResponseEntity.status(status).body(valid);
	}
	
	@GetMapping(value = "/{id}/pedidos")
	public ResponseEntity<List<Pedido>> findPedidoByUsuario(@PathVariable Long id) {
		try {
			List<Pedido> pedidos = usuarioService.findPedidoByUsuario(id);
			return ResponseEntity.status(HttpStatus.OK).body(pedidos);
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping(value = "/cadastrar")
	public ResponseEntity<UsuarioDTO> insert(@RequestBody Usuario usuario) {
		try {
			UsuarioDTO userDto = usuarioService.insert(usuario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(userDto);
		} catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		usuarioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
		try {
			UsuarioDTO userDto = usuarioService.updateUsuario(id, usuario);
			return ResponseEntity.ok().body(userDto);
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
