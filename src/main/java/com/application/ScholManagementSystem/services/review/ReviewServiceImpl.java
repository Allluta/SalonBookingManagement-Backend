package com.application.ScholManagementSystem.services.review;

import com.application.ScholManagementSystem.dto.ReviewRequest;
import com.application.ScholManagementSystem.entities.Hairdresser;
import com.application.ScholManagementSystem.entities.Reservation;
import com.application.ScholManagementSystem.entities.Review;
import com.application.ScholManagementSystem.repositories.HairdresserRepository;
import com.application.ScholManagementSystem.repositories.ReservationRepository;
import com.application.ScholManagementSystem.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HairdresserRepository hairdresserRepository;

    public List<Review> getReviewsByHairdresserId(Long hairdresserId) {
        return reviewRepository.findByHairdresserId(hairdresserId);
    }

    public Review addReview(ReviewRequest reviewRequest) {
        Review review = new Review();
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setHairdresserId(reviewRequest.getHairdresserId());
        review.setReservationId(reviewRequest.getReservationId());


        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
    public Review editReview(Long reviewId, ReviewRequest reviewRequest) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Opinia nie znaleziona"));

        existingReview.setRating(reviewRequest.getRating());
        existingReview.setComment(reviewRequest.getComment());

        // Zapisz zaktualizowaną opinię
        return reviewRepository.save(existingReview);
    }
    public boolean doesReviewExistForReservation(Long reservationId) {
        return reviewRepository.existsByReservationId(reservationId);
    }

    public Review getReviewByIdOrReservationId(Long reviewId) {
        Optional<Review> reviewById = reviewRepository.findById(reviewId);

        if (reviewById.isPresent()) {
            return reviewById.get();
        } else {
            List<Review> reviewsByReservationId = reviewRepository.findByReservationId(reviewId);
            return reviewsByReservationId.isEmpty() ? null : reviewsByReservationId.get(0);
        }
    }

    public Review getReviewByReservationAndHairdresser(Long reservationId, Long hairdresserId) {
        List<Review> reviews = reviewRepository.findByReservationIdAndHairdresserId(reservationId, hairdresserId);
        return reviews.isEmpty() ? null : reviews.get(0);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }



}
