package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class RatingDTO {
    private int rating_id;
    private  int rating;
    private int booking_id;
    private String comments;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rating_date;
    private BookingDTO bookingDTOS;
}
