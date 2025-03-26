package com.msx.jobms.job;

import com.msx.jobms.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping()
    public ResponseEntity<List<JobDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody Job job) {
        boolean success = jobService.create(job);

        if (!success) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        JobDTO jobDTO = jobService.findJobById(id);

        if (jobDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(jobDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.delete(id);

        if (deleted) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJob(@PathVariable Long id, @RequestBody Job job) {
        boolean updated = jobService.update(id, job);

        if (!updated) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
