package com.trip97.modules.review.model.service;

import com.trip97.modules.review.model.Review;

import java.util.List;

public interface ReviewService {

    Integer registerReview(Review review);
    List<Review> getReviews(Integer attractionId);
    Integer editReview(Review review);
    Integer removeReview(Integer id);
}
