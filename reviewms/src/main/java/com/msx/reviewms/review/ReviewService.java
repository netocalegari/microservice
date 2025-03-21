package com.msx.reviewms.review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews(Long companyId);

    Review findReview(Long reviewId);

    boolean create(Long companyId, Review review);

    boolean update(Long reviewId, Review review);

    boolean delete(Long reviewId);

}
