package com.example.demo.controller;


import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.OfficeDTO;
import com.example.demo.dto.PhotosDTO;
import com.example.demo.exception.OfficeNotFoundException;
import com.example.demo.services.api.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeService officeService;

    /**
     * Gets list of all offices from office table
     * Using JPA findAll()
     * @return officeDTOList
     */
    @GetMapping(value = "/all_offices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOffices() {
        try {
            return ResponseEntity.ok(officeService.getAllOffices());

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Checks for office availability on that specific date
     * Picks one of the available offices and saves the booking
     * @param chosenDate date chosen by User for the booking
     * @param bookingDTO instance
     * @return bookingDTO1
     */
    @PostMapping(value = "/add-Booking/{chosenDate}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBooking(@PathVariable("chosenDate") Date chosenDate, @RequestBody BookingDTO bookingDTO) {
        try {
            return ResponseEntity.ok(officeService.AddBookingsBasedOnOfficeAvailability(chosenDate, bookingDTO));
        }catch (OfficeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add-office", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addOffice(@RequestBody OfficeDTO officeDTO) {
        try {
            return ResponseEntity.ok(officeService.addOffice(officeDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/viewOfficesAndPhotos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewOfficesAndPhotos() {
        try {
            return ResponseEntity.ok(officeService.viewOfficesAndPhotos());

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
