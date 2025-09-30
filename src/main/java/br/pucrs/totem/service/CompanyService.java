package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Company;
import br.pucrs.totem.entity.BuildingCompany;
import br.pucrs.totem.repository.CompanyRepository;
import br.pucrs.totem.repository.BuildingCompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final BuildingCompanyRepository buildingCompanyRepository;

    public CompanyService(CompanyRepository companyRepository, BuildingCompanyRepository buildingCompanyRepository) {
        this.companyRepository = companyRepository;
        this.buildingCompanyRepository = buildingCompanyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return companyRepository.existsById(id);
    }

    public List<BuildingCompany> findBuildingsByCompanyId(Long companyId) {
        return buildingCompanyRepository.findByCompanyId(companyId);
    }

    public BuildingCompany saveBuildingCompany(BuildingCompany buildingCompany) {
        return buildingCompanyRepository.save(buildingCompany);
    }
}