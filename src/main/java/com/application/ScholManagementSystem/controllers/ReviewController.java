package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.dto.ReviewRequest;
import com.application.ScholManagementSystem.entities.Review;
import com.application.ScholManagementSystem.services.review.ReviewServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewServiceImpl.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody ReviewRequest reviewRequest) {
        try {
            // Sprawdź, czy istnieje już opinia dla tej rezerwacji
            if (reviewServiceImpl.doesReviewExistForReservation(reviewRequest.getReservationId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Opinia już istnieje dla tej rezerwacji");
            }

            Review addedReview = reviewServiceImpl.addReview(reviewRequest);
            return ResponseEntity.ok(addedReview);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas dodawania recenzji");
        }
    }
    @PutMapping("/edit/{reviewId}")
    public ResponseEntity<?> editReview(@PathVariable Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        try {
            Review editedReview = reviewServiceImpl.editReview(reviewId, reviewRequest);
            return ResponseEntity.ok(editedReview);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Opinia nie znaleziona");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas edycji recenzji");
        }
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        try {
            reviewServiceImpl.deleteReview(reviewId);
            return ResponseEntity.ok("Opinia usunięta pomyślnie");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Opinia nie znaleziona");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas usuwania recenzji");
        }
    }
    @GetMapping("/exists/{reservationId}")
    public ResponseEntity<Boolean> doesReviewExistForReservation(@PathVariable Long reservationId) {
        boolean doesExist = reviewServiceImpl.doesReviewExistForReservation(reservationId);
        return ResponseEntity.ok(doesExist);
    }

    @GetMapping("/reservation/{reservationId}/{hairdresserId}")
    public ResponseEntity<Review> getReviewByReservationId(@PathVariable Long reservationId, @PathVariable Long hairdresserId) {
        // Logika pobierania recenzji na podstawie reservationId i hairdresserId
        try {
            // Tutaj możesz użyć logiki związanej z pobieraniem recenzji na podstawie reservationId i hairdresserId
            // Na przykład:
            Review review = reviewServiceImpl.getReviewByReservationAndHairdresser(reservationId, hairdresserId);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/reservation/{reviewId}")
    public ResponseEntity<Review> getReviewByIdOrReservationId(@PathVariable Long reviewId) {
        try {
            // Tutaj używasz zintegrowanej metody z service
            Review review = reviewServiceImpl.getReviewByIdOrReservationId(reviewId);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}




