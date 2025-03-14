package com.msx.first_job_app.job.impl;

import com.msx.first_job_app.job.Job;
import com.msx.first_job_app.job.JobRepository;
import com.msx.first_job_app.job.JobService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    @Transactional
    public void create(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
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
        Job jobFound = findJobById(id);

        if (jobFound == null) return false;

        if (job.getTitle() != null) jobFound.setTitle(job.getTitle());
        if (job.getDescription() != null) jobFound.setDescription(job.getDescription());
        if (job.getMinSalary() != null) jobFound.setMinSalary(job.getMinSalary());
        if (job.getMaxSalary() != null) jobFound.setMaxSalary(job.getMaxSalary());
        if (job.getLocation() != null) jobFound.setLocation(job.getLocation());

        return true;
    }
}
