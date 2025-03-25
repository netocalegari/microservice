package com.msx.jobms.job.clients;

import com.msx.jobms.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWMS")
public interface ReviewClient {
    @GetMapping("/api/reviews")
    List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
