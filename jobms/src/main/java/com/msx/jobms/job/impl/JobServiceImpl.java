package com.msx.jobms.job.impl;

import com.msx.jobms.job.Job;
import com.msx.jobms.job.JobRepository;
import com.msx.jobms.job.JobService;
import com.msx.jobms.job.dto.JobWithCompanyDTO;
import com.msx.jobms.job.external.Company;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
//        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto).toList();
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

    private JobWithCompanyDTO convertToDto(Job job) {
//        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
//        jobWithCompanyDTO.setJob(job);

        RestTemplate restTemplate = new RestTemplate();

        Company company = restTemplate.getForObject(
                "http://localhost:8081/api/company/" + job.getCompanyId(),
                Company.class
        );

        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getLocation(),
//                job.getCompanyId(),
                company
        );
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }
}
