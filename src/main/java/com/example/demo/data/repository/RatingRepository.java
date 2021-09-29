package com.example.demo.data.repository;

import com.example.demo.data.domain.Rating;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {
    /**
     * Gets ratings based on officeID
     ********* SELECT r.* FROM rating r left join booking b on r.booking_id = b.booking_id where b.officeID=1;************
     *     SELECT r.* FROM rating r where r.rating > 0 and r.rating < 3;
     *     SELECT r.* FROM rating r where r.rating > 2 and r.rating < 4;
     *     SELECT r.* FROM rating r where r.rating > 3 and r.rating <= 5;
     *
     */

    List<Rating> findAll();

    @Query(value = "SELECT r.* FROM rating r left join booking b on r.booking_id = b.booking_id where b.userID= :chosenUserID", nativeQuery = true)
    List<Rating> getRatingsBasedOnUserID(@Param("chosenUserID") Integer chosenUserID);

    @Query(value = "SELECT r.* from rating r where r.booking_id= :chosenBookingID", nativeQuery = true)
    Rating getRatingByBookingID(@Param("chosenBookingID") Integer chosenBookingID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM rating r WHERE r.booking_id = :chosenBookingID", nativeQuery = true)
    void deleteRatingByBookingID(@Param("chosenBookingID") Integer chosenBookingID);

    @Query(value = "SELECT r.* FROM rating r left join booking b on r.booking_id = b.booking_id where b.officeID= :chosenOfficeID", nativeQuery = true)
    List<Rating> getRatingsBasedOnOfficeID(@Param("chosenOfficeID") Integer chosenOfficeID);


    @Query(value = "SELECT r.* FROM rating r where r.rating > 0 and r.rating < 3", nativeQuery = true)
    List<Rating> getAllNegativeRatings();

    @Query(value = "SELECT r.* FROM rating r where r.rating > 2 and r.rating < 4", nativeQuery = true)
    List<Rating> getAllNeutralRatings();

    @Query(value = "SELECT r.* FROM rating r where r.rating > 3 and r.rating <= 5", nativeQuery = true)
    List<Rating> getAllPositiveRatings();







}
