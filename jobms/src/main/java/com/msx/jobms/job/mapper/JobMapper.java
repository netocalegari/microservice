package com.msx.jobms.job.mapper;

import com.msx.jobms.job.Job;
import com.msx.jobms.job.dto.JobDTO;
import com.msx.jobms.job.external.Company;
import com.msx.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(Job job, Company company, List<Review> reviews) {
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getLocation(),
                company,
                reviews
        );
    }
}
