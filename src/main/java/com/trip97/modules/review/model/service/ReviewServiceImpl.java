package com.trip97.modules.review.model.service;

import com.trip97.modules.review.model.Review;
import com.trip97.modules.review.model.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    @Override
    public Integer registerReview(Review review) {
        return reviewMapper.insertReview(review);
    }

    @Override
    public List<Review> getReviews(Integer attractionId) {
        return reviewMapper.selectAllReviewsByAttractionId(attractionId);
    }

    @Override
    public Integer editReview(Review review) {
        return reviewMapper.updateReview(review);
    }

    @Override
    public Integer removeReview(Integer id) {
        return reviewMapper.deleteReview(id);
    }
}
