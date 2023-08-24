package com.livraria.livraria.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livraria.livraria.entities.Livro;
import com.livraria.livraria.entities.Pedido;
import com.livraria.livraria.entities.Usuario;
import com.livraria.livraria.entities.dtos.response.PedidoUpdateDTO;
import com.livraria.livraria.entities.enums.StatusDoPedido;
import com.livraria.livraria.repositories.PedidoRepository;
import com.livraria.livraria.repositories.UsuarioRepository;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private LivroService livroService;

	public Pedido findById(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Pedido> findAll() {
		List<Pedido> listPedido = pedidoRepository.findAll();
		return listPedido;
	}

	public Pedido insert(Pedido pedido, Long usuario_id) {
		try {
			Usuario usuario = usuarioRepository.findById(usuario_id).get();
			pedido.setUsuario(usuario);
			return pedidoRepository.save(pedido);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(usuario_id);
		}
	}

	public void deleteById(Long id) {
		try {
			pedidoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Pedido updatePedido(Long id, PedidoUpdateDTO novoPedido) {
		try {
			Pedido pedido = pedidoRepository.getReferenceById(id);
			updatePedido(pedido, novoPedido);

			// Atualizar o quantidade de vendas de cada livro do pedido
			if (pedido.getStatusDoPedido() == StatusDoPedido.APROVADO) {
				for (Livro livro : pedido.getLivros()) {
					livroService.updateQuantiVendasLivro(livro.getId());
				}
			}

			return pedidoRepository.save(pedido);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updatePedido(Pedido pedido, PedidoUpdateDTO novoPedido) {
		if (novoPedido.getStatusDoPedido() != pedido.getStatusDoPedido()) {
			pedido.setMomentUpdated(novoPedido.getMomentUpdated());
			pedido.setStatusDoPedido(novoPedido.getStatusDoPedido());
		}
	}
}