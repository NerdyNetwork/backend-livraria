package com.livraria.livraria.entities.dtos;

import java.time.Instant;

import com.livraria.livraria.entities.enums.StatusDoPedido;

public class PedidoUpdateDTO {
	private Instant moment = Instant.now();
	private Integer statusDoPedido;
	
	public PedidoUpdateDTO() {
	}
	
	public PedidoUpdateDTO(StatusDoPedido statusDoPedido) {
		setStatusDoPedido(statusDoPedido);
	}
	
	public Instant getMoment() {
		return moment;
	}
	
	public void setMoment(Instant moment) {
		this.moment = moment;
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
