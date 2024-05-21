package com.trip97.modules.review.controller;

import com.trip97.modules.hotPlace.model.HotPlace;
import com.trip97.modules.review.model.Review;
import com.trip97.modules.review.model.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/attraction/{attractionId}/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> getReviews(@PathVariable Integer attractionId) {
        List<Review> list = reviewService.getReviews(attractionId);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> registerReview(@PathVariable int attractionId, @RequestBody Review review) {
        reviewService.registerReview(review);
        List<Review> list = reviewService.getReviews(attractionId);
        return getListResponseEntity(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateReview(@PathVariable int attractionId, @RequestBody Review review) {
        reviewService.editReview(review);
        List<Review> list = reviewService.getReviews(attractionId);
        return getListResponseEntity(list);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeReview(@PathVariable int attractionId, @PathVariable int id) {
        reviewService.removeReview(id);
        List<Review> list = reviewService.getReviews(attractionId);
        if (list != null && !list.isEmpty()) {
            return getListResponseEntity(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    private static ResponseEntity<List<Review>> getListResponseEntity(List<Review> list) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(list);
    }

}
