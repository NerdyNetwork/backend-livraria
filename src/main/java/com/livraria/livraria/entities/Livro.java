package com.livraria.livraria.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.livraria.livraria.entities.enums.TipoLivro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_livro")
public class Livro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String descricao;
	private String editora;
	private String isbn;
	private int codigoDeBarras;
	private int quantidadeCompras = 0;
	
	private int tipoLivro;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "livros")
	private List<Pedido> pedidos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	
	@ManyToMany
	@JoinTable(name = "tb_livro_categoria", joinColumns = @JoinColumn(name = "livro_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> categorias = new HashSet<>();
	
	public Livro() {
	}

	//Construtor para livro que tem fornecedor conhecido
	public Livro(Long id, String nome, String descricao, String editora, String isbn, int codigoDeBarras, TipoLivro tipoLivro, Autor autor, Fornecedor fornecedor) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.editora = editora;
		this.isbn = isbn;
		this.codigoDeBarras = codigoDeBarras;
		setTipoLivro(tipoLivro);
		this.autor = autor;
		this.fornecedor = fornecedor;
	}
	
	//Construtor para livro sem fornecedor
	public Livro(Long id, String nome, String descricao, String editora, String isbn, int codigoDeBarras, TipoLivro tipoLivro, Autor autor) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.editora = editora;
		this.isbn = isbn;
		this.codigoDeBarras = codigoDeBarras;
		setTipoLivro(tipoLivro);
		this.autor = autor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(int codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}
	
	public int getQuantidadeCompras() {
		return quantidadeCompras;
	}
	
	public void setQuantidadeCompras(int quantidadeCompras) {
		this.quantidadeCompras = quantidadeCompras;
	}

	public TipoLivro getTipoLivro() {
		return TipoLivro.valueOf(tipoLivro);
	}

	public void setTipoLivro(TipoLivro tipoLivro) {
		if(tipoLivro != null) {
			this.tipoLivro = tipoLivro.getCode();
		}
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void atualizarQuantidadeCompras() {
		setQuantidadeCompras(getQuantidadeCompras() + 1);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return id == other.id;
	}	
}