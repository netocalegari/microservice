package com.msx.jobms.job.impl;

import com.msx.jobms.job.Job;
import com.msx.jobms.job.JobRepository;
import com.msx.jobms.job.JobService;
import com.msx.jobms.job.clients.CompanyClient;
import com.msx.jobms.job.clients.ReviewClient;
import com.msx.jobms.job.dto.JobDTO;
import com.msx.jobms.job.external.Company;
import com.msx.jobms.job.external.Review;
import com.msx.jobms.job.mapper.JobMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::convertToDto).toList();
    }

    @Override
    @Transactional
    public void create(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO findJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);

        if (job == null) return null;

        return convertToDto(job);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!jobRepository.existsById(id)) {
            return false;
        }

        jobRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Long id, Job job) {
        Job jobFound = jobRepository.findById(id).orElse(null);

        if (jobFound == null) return false;

        if (job.getTitle() != null) jobFound.setTitle(job.getTitle());
        if (job.getDescription() != null) jobFound.setDescription(job.getDescription());
        if (job.getMinSalary() != null) jobFound.setMinSalary(job.getMinSalary());
        if (job.getMaxSalary() != null) jobFound.setMaxSalary(job.getMaxSalary());
        if (job.getLocation() != null) jobFound.setLocation(job.getLocation());

        return true;
    }

    private JobDTO convertToDto(Job job) {
        Company company = companyClient.getCompany(job.getCompanyId());

        List<Review> reviews = reviewClient.getReviews(company.getId());

        return JobMapper.mapToJobWithCompanyDto(job, company, reviews);
    }
}
