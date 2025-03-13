package com.msx.first_job_app.job.impl;

import com.msx.first_job_app.job.Job;
import com.msx.first_job_app.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void create(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobs.stream().filter(job -> job.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        Job job = getJobById(id);

        if (job == null) return false;

        return jobs.remove(job);
    }

    @Override
    public boolean update(Long id, Job job) {
        Job jobFound = getJobById(id);

        if (jobFound == null) return false;

        if (job.getTitle() != null) jobFound.setTitle(job.getTitle());
        if (job.getDescription() != null) jobFound.setDescription(job.getDescription());
        if (job.getMinSalary() != null) jobFound.setMinSalary(job.getMinSalary());
        if (job.getMaxSalary() != null) jobFound.setMaxSalary(job.getMaxSalary());
        if (job.getLocation() != null) jobFound.setLocation(job.getLocation());

        return true;
//        Job updatedJob = new Job(
//                jobFound.getId(),
//                job.getTitle() != null ? job.getTitle() : jobFound.getTitle(),
//                job.getDescription() != null ? job.getDescription() : jobFound.getDescription(),
//                job.getMinSalary() != null ? job.getMinSalary() : jobFound.getMinSalary(),
//                job.getMaxSalary() != null ? job.getMaxSalary() : jobFound.getMaxSalary(),
//                job.getLocation() != null ? job.getLocation() : jobFound.getLocation()
//        );

//        delete(jobFound.getId());
//        jobs.add(updatedJob);

//        return true;
    }
}
