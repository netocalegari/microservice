package com.msx.jobms.job;

import com.msx.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();

    void create(Job job);

    Job findJobById(Long id);

    boolean delete(Long id);

    boolean update(Long id, Job job);
}
