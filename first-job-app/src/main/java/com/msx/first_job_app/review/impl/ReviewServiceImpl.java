package com.msx.first_job_app.review.impl;

import com.msx.first_job_app.company.Company;
import com.msx.first_job_app.company.CompanyService;
import com.msx.first_job_app.review.Review;
import com.msx.first_job_app.review.ReviewRepository;
import com.msx.first_job_app.review.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> findAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review findReview(Long companyId, Long reviewId) {
        return reviewRepository.findReviewByCompanyAndId(companyId, reviewId).orElse(null);
    }

    @Override
    @Transactional
    public boolean create(Long companyId, Review review) {
        Company company = companyService.findCompanyById(companyId);

        if (company == null) return false;

        review.setCompany(company);
        reviewRepository.save(review);

        return true;
    }

    @Override
    @Transactional
    public boolean update(Long companyId, Long reviewId, Review updatedReview) {
        Company company = companyService.findCompanyById(companyId);

        if (company == null) return false;

        Review existingReview = reviewRepository.findReviewByCompanyAndId(companyId, reviewId).orElse(null);

        if (existingReview == null) return false;

        if (updatedReview.getTitle() != null) existingReview.setTitle(updatedReview.getTitle());
        if (updatedReview.getDescription() != null) existingReview.setDescription(updatedReview.getDescription());
        if (updatedReview.getRating() != null) existingReview.setRating(updatedReview.getRating());

        reviewRepository.save(existingReview);

        return true;
    }


    @Override
    @Transactional
    public boolean delete(Long companyId, Long reviewId) {
        Company company = companyService.findCompanyById(companyId);

        if (company == null) return false;

        Review existingReview = reviewRepository.findReviewByCompanyAndId(companyId, reviewId).orElse(null);

        if (existingReview == null) return false;

        reviewRepository.deleteById(reviewId);

        return true;
    }
}
