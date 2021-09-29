package com.example.demo.services.api;

import com.example.demo.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    List<RatingDTO> getAllRatings();
    RatingDTO addRating(RatingDTO ratingDTO);
    List<RatingDTO> getRatingsBasedOnUserID(Integer userID);
    RatingDTO updateRatingByBookingID(Integer bookingID, RatingDTO ratingDTO);
    void deleteRatingByBookingID(Integer bookingID);
    List<RatingDTO> getAllPositiveRatings();
    List<RatingDTO> getAllNegativeRatings();
    List<RatingDTO> getAllNeutralRatings();
    List<RatingDTO> getRatingsBasedOnOfficeID(Integer officeID);



}
