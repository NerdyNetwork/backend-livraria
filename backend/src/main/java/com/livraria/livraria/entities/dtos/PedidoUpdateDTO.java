package com.livraria.livraria.entities.dtos;

import java.time.Instant;

import com.livraria.livraria.entities.enums.StatusDoPedido;

public class PedidoUpdateDTO {
	private Instant momentUpdated = Instant.now();
	private Integer statusDoPedido;
	
	public PedidoUpdateDTO() {
	}
	
	public PedidoUpdateDTO(StatusDoPedido statusDoPedido) {
		setStatusDoPedido(statusDoPedido);
	}
	
	public Instant getMomentUpdated() {
		return momentUpdated;
	}
	
	public void setMomentUpdated(Instant momentUpdated) {
		this.momentUpdated = momentUpdated;
	}
	
	public StatusDoPedido getStatusDoPedido() {
		return StatusDoPedido.valueOf(statusDoPedido);
	}

	public void setStatusDoPedido(StatusDoPedido statusDoPedido) {
		if(statusDoPedido != null) {
			this.statusDoPedido = statusDoPedido.getCode();
		}
	}
}
