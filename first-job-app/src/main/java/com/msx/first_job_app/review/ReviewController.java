package com.msx.first_job_app.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> findAllReviews(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.findAllReviews(companyId));
    }

    @PostMapping("/reviews")
    public ResponseEntity<Boolean> create(@PathVariable Long companyId, @RequestBody Review review) {
        boolean success = reviewService.create(companyId, review);

        if (!success) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> findReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.findReview(companyId, reviewId);

        if (review == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(review);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> update(
            @PathVariable Long companyId,
            @PathVariable Long reviewId,
            @RequestBody Review review
    ) {
        boolean updated = reviewService.update(companyId, reviewId, review);

        if (!updated) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long companyId,
            @PathVariable Long reviewId
    ) {
        boolean deleted = reviewService.delete(companyId, reviewId);

        if (!deleted) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
