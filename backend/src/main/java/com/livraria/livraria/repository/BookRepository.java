package com.livraria.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.livraria.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
