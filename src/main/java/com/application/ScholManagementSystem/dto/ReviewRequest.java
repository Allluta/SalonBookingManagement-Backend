package com.application.ScholManagementSystem.dto;

import com.application.ScholManagementSystem.entities.Hairdresser;
import com.application.ScholManagementSystem.entities.Reservation;

public class ReviewRequest {
    private int rating;
    private String comment;
    private Long hairdresserId;
    private Long reservationId;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getHairdresserId() {
        return hairdresserId;
    }

    public void setHairdresserId(Long hairdresserId) {
        this.hairdresserId = hairdresserId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
