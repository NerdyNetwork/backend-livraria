package com.livraria.livraria.entities.dtos.response;

import java.io.Serializable;

public record UserDTO(Long id, String name, String email, String phone) implements Serializable {

}
