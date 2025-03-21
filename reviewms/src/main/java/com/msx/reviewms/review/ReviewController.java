package com.msx.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ResponseEntity<List<Review>> findAllReviews(@RequestParam Long companyId) {
        return ResponseEntity.ok(reviewService.findAllReviews(companyId));
    }

    @PostMapping()
    public ResponseEntity<Boolean> create(@RequestParam Long companyId, @RequestBody Review review) {
        boolean success = reviewService.create(companyId, review);

        if (!success) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> findReview(@PathVariable Long reviewId) {
        Review review = reviewService.findReview(reviewId);

        if (review == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(review);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> update(@PathVariable Long reviewId, @RequestBody Review review) {
        boolean updated = reviewService.update(reviewId, review);

        if (!updated) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> delete(@PathVariable Long reviewId) {
        boolean deleted = reviewService.delete(reviewId);

        if (!deleted) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
