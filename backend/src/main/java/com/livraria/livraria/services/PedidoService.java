package com.livraria.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livraria.livraria.entities.Pedido;
import com.livraria.livraria.entities.dtos.PedidoUpdateDTO;
import com.livraria.livraria.repositories.PedidoRepository;
import com.livraria.livraria.services.exceptions.DatabaseException;
import com.livraria.livraria.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido findById(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Pedido> findAll() {
		List<Pedido> listPedido = pedidoRepository.findAll();
		return listPedido;
	}
	
	public Pedido insert(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public void deleteById(Long id) {
		try {
			pedidoRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Pedido updatePedido(Long id, PedidoUpdateDTO novoPedido) {
		try {
			Pedido pedido = pedidoRepository.getReferenceById(id);
			updatePedido(pedido, novoPedido);
			return pedidoRepository.save(pedido);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updatePedido(Pedido pedido, PedidoUpdateDTO novoPedido) {
		if(novoPedido.getStatusDoPedido() != pedido.getStatusDoPedido()) {
			pedido.setMomentUpdated(novoPedido.getMomentUpdated());
			pedido.setStatusDoPedido(novoPedido.getStatusDoPedido());
		}
	}
}