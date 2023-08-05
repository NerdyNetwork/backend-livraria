package com.livraria.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.livraria.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    public Livro findByNome(String nome);
}