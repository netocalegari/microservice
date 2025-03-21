package com.msx.companyms.company;

import java.util.List;

public interface CompanyService {
    List<Company> findAllCompanies();

    Company findCompanyById(Long id);

    boolean create(Company company);

    boolean update(Long id, Company company);

    boolean delete(Long id);
}
