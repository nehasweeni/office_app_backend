package com.example.demo.dto;

import lombok.Data;

import java.util.List;

/**
 * office table DTO
 */
@Data
public class OfficeDTO {
    private int office_id;
    private String office_name;
    private String office_address;
    private List<PhotosDTO> photosDTOS;

}
