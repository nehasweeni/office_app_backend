package com.example.demo.services.impl;

import com.example.demo.data.domain.Booking;
import com.example.demo.data.domain.Rating;
import com.example.demo.data.repository.BookingRepository;
import com.example.demo.data.repository.RatingRepository;
import com.example.demo.dto.RatingDTO;
import com.example.demo.services.api.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final BookingRepository bookingRepository;

    @Override
    public List<RatingDTO> getAllRatings() {
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        List<Rating> ratings = ratingRepository.findAll();

        ratings.forEach(rating -> {
            RatingDTO ratingDTO = convertToRatingDTO(rating);
            ratingDTOList.add(ratingDTO);

        });
        System.out.println(ratingDTOList);
        return ratingDTOList;
    }

    @Override
    public RatingDTO addRating(RatingDTO ratingDTO) {
        Rating rating = new Rating();

        rating.setRating_id(ratingDTO.getRating_id());
        rating.setRating(ratingDTO.getRating());

        Booking booking = bookingRepository.getById(ratingDTO.getBooking_id());
        rating.setBooking(booking);

        rating.setComments(ratingDTO.getComments());
        rating.setRating_date(ratingDTO.getRating_date());

        RatingDTO new_Rating = new RatingDTO();

        Rating newRating = ratingRepository.save(rating);

        new_Rating.setRating_id(newRating.getRating_id());
        new_Rating.setRating(newRating.getRating());
        new_Rating.setBooking_id(newRating.getBooking().getBooking_id());
        new_Rating.setComments(newRating.getComments());
        new_Rating.setRating_date(newRating.getRating_date());

        return new_Rating;
    }

    @Override
    public List<RatingDTO> getRatingsBasedOnUserID(Integer userID) {
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        List<Rating> ratingsBasedOnUserID = ratingRepository.getRatingsBasedOnUserID(userID);

        ratingsBasedOnUserID.forEach(rating -> {
            RatingDTO ratingDTO = convertToRatingDTO(rating);
            ratingDTOList.add(ratingDTO);
        });
        System.out.println(ratingDTOList);
        return ratingDTOList;
    }


    @Override
    public RatingDTO updateRatingByBookingID(Integer bookingID, RatingDTO ratingDTO) {
        Rating rating = ratingRepository.getRatingByBookingID(bookingID);

        if (rating.getRating_id() > 0) {
            rating.setRating_id(ratingDTO.getRating_id());
            rating.setRating(ratingDTO.getRating());

            Booking booking = bookingRepository.getById(ratingDTO.getBooking_id());
            rating.setBooking(booking);

            rating.setComments(ratingDTO.getComments());
            rating.setRating_date(ratingDTO.getRating_date());
            RatingDTO updated_rating = new RatingDTO();
            Rating updatedRating = ratingRepository.save(rating);

            updated_rating.setRating_id(updatedRating.getRating_id());
            updated_rating.setRating(updatedRating.getRating());
            updated_rating.setBooking_id(updatedRating.getBooking().getBooking_id());
            updated_rating.setComments(updatedRating.getComments());
            updated_rating.setRating_date(updatedRating.getRating_date());

            return updated_rating;
        } else {
            return null;
        }
    }

    @Override
    public void deleteRatingByBookingID(Integer bookingID) {
        ratingRepository.deleteRatingByBookingID(bookingID);

    }

    @Override
    public List<RatingDTO> getAllPositiveRatings() {
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        List<Rating> ratings = ratingRepository.getAllPositiveRatings();

        ratings.forEach(rating -> {
            RatingDTO ratingDTO = convertToRatingDTO(rating);
            ratingDTOList.add(ratingDTO);

        });
        System.out.println(ratingDTOList);
        return ratingDTOList;
    }

    @Override
    public List<RatingDTO> getAllNegativeRatings() {
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        List<Rating> ratings = ratingRepository.getAllNegativeRatings();

        ratings.forEach(rating -> {
            RatingDTO ratingDTO = convertToRatingDTO(rating);
            ratingDTOList.add(ratingDTO);

        });
        System.out.println(ratingDTOList);
        return ratingDTOList;
    }

    @Override
    public List<RatingDTO> getAllNeutralRatings() {
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        List<Rating> ratings = ratingRepository.getAllNeutralRatings();


        ratings.forEach(rating -> {
            RatingDTO ratingDTO = convertToRatingDTO(rating);
            ratingDTOList.add(ratingDTO);

        });
        System.out.println(ratingDTOList);
        return ratingDTOList;
    }

    @Override
    public List<RatingDTO> getRatingsBasedOnOfficeID(Integer officeID) {
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        List<Rating> ratingsBasedOnOfficeID = ratingRepository.getRatingsBasedOnOfficeID(officeID);

        ratingsBasedOnOfficeID.forEach(rating -> {
            RatingDTO ratingDTO = convertToRatingDTO(rating);
            ratingDTOList.add(ratingDTO);
        });
        System.out.println(ratingDTOList);
        return ratingDTOList;
    }

    RatingDTO convertToRatingDTO(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();

        ratingDTO.setRating_id(rating.getRating_id());
        ratingDTO.setRating(rating.getRating());
        ratingDTO.setBooking_id(rating.getBooking().getBooking_id());
        ratingDTO.setComments(rating.getComments());
        ratingDTO.setRating_date(rating.getRating_date());

        return ratingDTO;
    }

}
