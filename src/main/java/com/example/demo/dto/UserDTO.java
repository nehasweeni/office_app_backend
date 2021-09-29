package com.example.demo.dto;

import lombok.Data;

/**
 * user_reg table DTO
 */
@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;

}
