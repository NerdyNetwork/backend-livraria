package com.livraria.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livraria.livraria.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}