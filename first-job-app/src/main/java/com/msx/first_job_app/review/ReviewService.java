package com.msx.first_job_app.review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews(Long companyId);

    Review findReview(Long companyId, Long reviewId);

    boolean create(Long companyId, Review review);

    boolean update(Long companyId, Long reviewId, Review review);

    boolean delete(Long companyId, Long reviewId);

}
