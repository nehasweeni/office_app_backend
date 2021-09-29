package com.example.demo.services.api;

import com.example.demo.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    /**
     * Get all bookings from booking table
     * @return bookingDTOList
     * Using JPA
     */
    List<BookingDTO> getAllBookings();

    /**
     * Get all bookings from booking table
     * @return bookingDTOList
     * Using Native Query
     */
    List<BookingDTO> getAllBookingsUsingNativeQuery();

    /**
     * Gets list all bookings from booking table for a specific User
     * @param userID Used to get bookings of the User
     * @return bookingDTOList
     */
    List<BookingDTO> getAllBookingsByUserID(Integer userID) ;

    /**
     * Deletes a specific booking for a specific User
     * @param userID Deletes booking of User based on the userID and:
     * @param booking_id based on booking_id
     */
    void deleteBookingByID(Integer userID, Integer booking_id);

    /**
     * Gets list of all previous bookings of a specific User
     * @param userID contains ID of User searching for previous bookings
     * @return bookingDTOList
     */
    List<BookingDTO> getPreviousBookingOfUser(Integer userID);

    /**
     * Gets list of all current bookings of a specific User
     * @param userID contains ID of User searching for current bookings
     * @return bookingDTOList
     */
    List<BookingDTO> getCurrentBookingOfUser(Integer userID);

    /**
     * Add bookings to Bookings table
     * @param bookingDTO instance
     * @return new_Booking
     */
    BookingDTO addBooking(BookingDTO bookingDTO);

}
