package com.livraria.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livraria.livraria.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
