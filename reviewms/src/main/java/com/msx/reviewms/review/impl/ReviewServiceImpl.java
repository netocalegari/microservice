package com.msx.reviewms.review.impl;

import com.msx.reviewms.review.Review;
import com.msx.reviewms.review.ReviewRepository;
import com.msx.reviewms.review.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review findReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    @Transactional
    public boolean create(Long companyId, Review review) {
        if (companyId == null || review == null) return false;

        review.setCompanyId(companyId);
        reviewRepository.save(review);

        return true;
    }

    @Override
    @Transactional
    public boolean update(Long reviewId, Review updatedReview) {
//        Company company = companyService.findCompanyById(companyId);

        if (reviewId == null) return false;

        Review existingReview = reviewRepository.findById(reviewId).orElse(null);

        if (existingReview == null) return false;

        if (updatedReview.getTitle() != null) existingReview.setTitle(updatedReview.getTitle());
        if (updatedReview.getDescription() != null) existingReview.setDescription(updatedReview.getDescription());
        if (updatedReview.getRating() != null) existingReview.setRating(updatedReview.getRating());
        if (updatedReview.getCompanyId() != null) existingReview.setCompanyId(updatedReview.getCompanyId());

        reviewRepository.save(existingReview);

        return true;
    }


    @Override
    @Transactional
    public boolean delete(Long reviewId) {
        if (reviewId == null) return false;

        Review existingReview = reviewRepository.findById(reviewId).orElse(null);

        if (existingReview == null) return false;

        reviewRepository.deleteById(reviewId);

        return true;
    }
}
