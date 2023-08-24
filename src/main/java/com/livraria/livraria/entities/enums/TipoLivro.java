package com.livraria.livraria.entities.enums;

public enum TipoLivro {
	LIVRO(1),
	REVISTA(2),
	JORNAL(3),
	ARTIGOCIENTIFICO(4);
	
	private int code;
	
	private TipoLivro(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static TipoLivro valueOf(int code) {
		for(TipoLivro livro : TipoLivro.values()) {
			if(livro.getCode() == code) {
				return livro;
			}
		}
		throw new IllegalArgumentException("Código do tipo do livro inválido");
	}
}
