package com.example.demo.data.repository;

import com.example.demo.data.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer>, JpaRepository<Booking, Integer> {
    List<Booking> findAll();

    @Query(value = "SELECT * FROM booking", nativeQuery = true)
    List<Booking> findAllBookings();

    @Query(value = "SELECT * FROM booking LEFT JOIN user_reg ON booking.userID=user_reg.id " +
            "LEFT JOIN office ON booking.officeID=office.office_id", nativeQuery = true)
    List<Booking> getAllBookings();


    @Query(value = "SELECT * FROM booking LEFT JOIN user_reg ON booking.userID=user_reg.id " +
            "LEFT JOIN office ON booking.officeID=office.office_id " +
            "where userID = :userID and booking_date < CURRENT_DATE ", nativeQuery = true)
    List<Booking> getPreviousBookingsOfUser(@Param("userID") Integer userID);

    @Query(value = "SELECT * FROM booking LEFT JOIN user_reg ON booking.userID=user_reg.id " +
            "LEFT JOIN office ON booking.officeID=office.office_id " +
            "where userID = :userID and booking_date > CURRENT_DATE ", nativeQuery = true)
    List<Booking> getCurrentBookingsOfUser(@Param("userID") Integer userID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM booking WHERE booking.userID = :userID " +
            "and booking.booking_id = :booking_id", nativeQuery = true)
    void deleteBookingByUserAndBooking_id(@Param("userID") Integer userID, @Param("booking_id") Integer booking_id);


}
