package com.livraria.livraria.entities.dtos.response;

import java.io.Serializable;

public class BookDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private int amount;

    public BookDTO() {
    }

    public BookDTO(Long id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }   

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
