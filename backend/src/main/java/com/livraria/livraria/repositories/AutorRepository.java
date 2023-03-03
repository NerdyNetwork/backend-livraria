package com.livraria.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.livraria.entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}