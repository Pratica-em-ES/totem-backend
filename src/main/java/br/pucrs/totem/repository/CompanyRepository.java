package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucrs.totem.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}