package com.application.ScholManagementSystem.repositories;

import com.application.ScholManagementSystem.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHairdresserId(Long hairdresserId);

    List<Review> findByReservationId(Long reservationId);
    boolean existsByReservationId(Long reservationId);



    List<Review> findByReservationIdAndHairdresserId(Long reservationId, Long hairdresserId);



}
