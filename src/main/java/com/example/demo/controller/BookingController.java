package com.example.demo.controller;

import com.example.demo.dto.BookingDTO;
import com.example.demo.services.api.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    /**
     * Gets list of all bookings from booking table
     * @return bookingDTOList
     * Using Native Query
     */

    @GetMapping(value = "/all_bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllBookings() {
        try {
            return ResponseEntity.ok(bookingService.getAllBookingsUsingNativeQuery());

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Gets list all bookings from booking table for a specific User
     * @param userID Used to get bookings of the User
     * @return bookingDTOList
     */

    @GetMapping(value = "/getBookingsByUserID/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBookingByUserID(@PathVariable("userID") Integer userID) {
        try {
            return ResponseEntity.ok(bookingService.getAllBookingsByUserID(userID));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    /**
     * Add bookings to Bookings table
     * @param bookingDTO instance
     * @return new_Booking
     */

    @PostMapping(value = "/add-Booking", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            return ResponseEntity.ok(bookingService.addBooking(bookingDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Gets list of all previous bookings of a specific User
     * @param userID contains ID of User searching for previous bookings
     * @return bookingDTOList
     */
    @GetMapping(value = "/getPreviousBookingsOfUser/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPreviousBookingOfUser(@PathVariable("userID") Integer userID) {
        try {
            return ResponseEntity.ok(bookingService.getPreviousBookingOfUser(userID));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    /**
     * Gets list of all current bookings of a specific User
     * @param userID contains ID of User searching for current bookings
     * @return bookingDTOList
     */
    @GetMapping(value = "/getCurrentBookingsOfUser/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCurrentBookingsOfUser(@PathVariable("userID") Integer userID) {
        try {
            return ResponseEntity.ok(bookingService.getCurrentBookingOfUser(userID));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    /**
     * Deletes a specific booking for a specific User
     * @param userID Deletes booking of User based on the userID and:
     * @param booking_id based on booking_id
     */

    @DeleteMapping(value = "/deleteBooking/{userID}/{booking_id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userID") Integer userID, @PathVariable("booking_id") Integer booking_id ) {
        try {
            bookingService.deleteBookingByID(userID, booking_id);
            return new ResponseEntity<>("Booking deleted", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
