package com.livraria.livraria.entities.dtos.response;

import java.io.Serializable;

public record BookDTO(Long id, String name, int amount) implements Serializable {

}
