package com.livraria.livraria.entities.enums;

public enum StatusDoPedido {
	PENDENTE(1),
	APROVADO(2),
	NEGADO(3),
	CANCELADO(4);
	
	private int code;
	
	private StatusDoPedido(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static StatusDoPedido valueOf(int code) {
		for(StatusDoPedido pedido : StatusDoPedido.values()) {
			if(pedido.getCode() == code) {
				return pedido;
			}
		}
		throw new IllegalArgumentException("Código do Status do Pedido inválido");
	}
}
