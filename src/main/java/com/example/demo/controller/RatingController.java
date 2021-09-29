package com.example.demo.controller;


import com.example.demo.dto.RatingDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.api.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @GetMapping(value = "/all_ratings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllRatings() {
        try {
            return ResponseEntity.ok(ratingService.getAllRatings());

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping(value = "/add_Rating", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@RequestBody RatingDTO ratingDTO) {
        try {
            return ResponseEntity.ok(ratingService.addRating(ratingDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/getRatingBasedOnUserID/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllRatingsBasedOnUserID(@PathVariable("userID") Integer userID) {
        try {
            return ResponseEntity.ok(ratingService.getRatingsBasedOnUserID(userID));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/update-Rating/{booking_id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> updateRatingUsingBookingID(@PathVariable("booking_id") Integer booking_id, @RequestBody RatingDTO ratingDTO) {
        try {
            ratingService.updateRatingByBookingID(booking_id, ratingDTO);
            return new ResponseEntity<>("Rating updated", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteRating/{booking_id}")
    public ResponseEntity<?> deleteRatingUsingBookingID(@PathVariable("booking_id") Integer booking_id) {
        try {
            ratingService.deleteRatingByBookingID(booking_id);
            return new ResponseEntity<>("Rating deleted", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/getRatingBasedOnOfficeID/{officeID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRatingsBasedOnOfficeID(@PathVariable("officeID") Integer officeID) {
        try {
            return ResponseEntity.ok(ratingService.getRatingsBasedOnUserID(officeID));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/getAllPositiveRatings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPositiveRatings() {
        try {
            return ResponseEntity.ok(ratingService.getAllPositiveRatings());

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/getAllNegativeRatings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllNegativeRatings() {
        try {
            return ResponseEntity.ok(ratingService.getAllNegativeRatings());

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/getAllNeutralRatings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllNeutralRatings() {
        try {
            return ResponseEntity.ok(ratingService.getAllNeutralRatings());

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
