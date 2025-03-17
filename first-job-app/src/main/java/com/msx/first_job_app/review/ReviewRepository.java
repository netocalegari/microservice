package com.msx.first_job_app.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId);

    @Query("SELECT r FROM Review r WHERE r.company.id = :companyId AND r.id = :reviewId")
    Optional<Review> findReviewByCompanyAndId(@Param("companyId") Long companyId, @Param("reviewId") Long reviewId);

}
