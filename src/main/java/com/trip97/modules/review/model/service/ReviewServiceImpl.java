package com.trip97.modules.review.model.service;

import com.trip97.modules.attraction.model.mapper.AttractionMapper;
import com.trip97.modules.review.model.Review;
import com.trip97.modules.review.model.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final AttractionMapper attractionMapper;
    private final ReviewMapper reviewMapper;

    @Override
    public Integer registerReview(Review review) {
        double currentRating = attractionMapper.getRating(review.getAttractionId());
        int currentReviewCount = attractionMapper.getReviewCount(review.getAttractionId());

        int reviewRating = review.getRating();

        double newRating = ((currentRating * currentReviewCount) + reviewRating) / (currentReviewCount + 1);

        attractionMapper.updateRating(newRating, review.getAttractionId());
        attractionMapper.updateReviewCount(currentReviewCount + 1, review.getAttractionId());

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
