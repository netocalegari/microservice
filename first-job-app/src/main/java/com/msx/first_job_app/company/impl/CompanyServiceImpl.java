package com.msx.first_job_app.company.impl;

import com.msx.first_job_app.company.Company;
import com.msx.first_job_app.company.CompanyRepository;
import com.msx.first_job_app.company.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean create(Company company) {
        List<Company> companies = findAllCompanies();

        Company companyExists = companies.stream()
                                         .filter(c -> c.getName().equals(company.getName()))
                                         .findFirst()
                                         .orElse(null);

        if (companyExists != null) return false;

        companyRepository.save(company);

        return true;
    }

    @Override
    @Transactional
    public boolean update(Long id, Company company) {
        Company companyToUpdate = findCompanyById(id);

        if (companyToUpdate == null) return false;

        if (company.getName() != null) companyToUpdate.setName(company.getName());
        if (company.getDescription() != null) companyToUpdate.setDescription(company.getDescription());
        if (company.getJobs() != null) companyToUpdate.setJobs(company.getJobs());

        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!companyRepository.existsById(id)) {
            return false;
        }

        companyRepository.deleteById(id);
        return true;
    }
}
