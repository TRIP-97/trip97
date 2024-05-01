package com.trip97.modules.review.model.mapper;

import com.trip97.modules.review.model.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    Integer insertReview(Review review);
    List<Review> selectAllReviewsByAttractionId(Integer attractionId);
    Integer updateReview(Review review);
    Integer deleteReview(Integer id);
}
