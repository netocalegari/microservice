package com.msx.jobms.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    void create(Job job);

    Job findJobById(Long id);

    boolean delete(Long id);

    boolean update(Long id, Job job);
}
