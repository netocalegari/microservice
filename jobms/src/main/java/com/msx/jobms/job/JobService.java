package com.msx.jobms.job;

import com.msx.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();

    void create(Job job);

    JobDTO findJobById(Long id);

    boolean delete(Long id);

    boolean update(Long id, Job job);
}
