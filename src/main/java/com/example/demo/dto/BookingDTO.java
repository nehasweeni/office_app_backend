package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

/**
 * Booking table DTO
 */
@Data
public class BookingDTO {
    private int booking_id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date booking_date;
    private int userID;
    private int officeID;
    private UserDTO userDTOS;
    private OfficeDTO officeDTO;


}
