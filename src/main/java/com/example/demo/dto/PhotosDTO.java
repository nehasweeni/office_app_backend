package com.example.demo.dto;

import lombok.Data;

import javax.persistence.Lob;

@Data
public class PhotosDTO {

    private int photos_id;
    private int office_id;
    @Lob
    private byte[] photo;
    private String content_type;

}
