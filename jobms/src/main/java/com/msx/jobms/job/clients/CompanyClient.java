package com.msx.jobms.job.clients;

import com.msx.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANYMS")
public interface CompanyClient {
    @GetMapping("/api/company/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
