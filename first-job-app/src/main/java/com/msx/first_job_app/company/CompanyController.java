package com.msx.first_job_app.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAllCompanies() {
        return ResponseEntity.ok(companyService.findAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long id) {
        Company company = companyService.findCompanyById(id);

        if (company == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody Company company) {
        boolean success = companyService.create(company);

        if (!success) return new ResponseEntity<>(HttpStatus.CONFLICT);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody Company company) {
        boolean success = companyService.update(id, company);

        if (!success) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Boolean> delete (@PathVariable Long id) {
        boolean success = companyService.delete(id);

        if (!success) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
