package com.livraria.livraria.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.livraria.livraria.entities.enums.StatusDoPedido;

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
@Table(name = "tb_pedido")
@CrossOrigin("*")
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant momentCreated = Instant.now();
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant momentUpdated = Instant.now();
	
	private Integer statusDoPedido;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable(name = "tb_pedido_livro", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "livro_id"))
	private List<Livro> livros = new ArrayList<>();
	
	public Pedido() {
	}
	
	public Pedido(Long id, StatusDoPedido statusDoPedido, Usuario usuario) {
		this.id = id;
		setStatusDoPedido(statusDoPedido);
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public Instant getMomentCreated() {
		return momentCreated;
	}

	public void setMomentCreated(Instant momentCreated) {
		this.momentCreated = momentCreated;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Livro> getLivros() {
		return livros;
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
		Pedido other = (Pedido) obj;
		return id == other.id;
	}
}