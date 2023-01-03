package com.dzm.evsms.company;

import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompanyService {

	private final CompanyRepository companyRepository;

	public Set<Long> getAllChildCompanies(Long rootCompanyId) {
		return companyRepository.findAllChildCompanies(rootCompanyId);
	}

}
