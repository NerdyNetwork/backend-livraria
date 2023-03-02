package com.livraria.livraria.entities.enums;

public enum StatusDoPedido {
	APROVADO(1),
	NEGADO(2),
	CANCELADO(3);
	
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
