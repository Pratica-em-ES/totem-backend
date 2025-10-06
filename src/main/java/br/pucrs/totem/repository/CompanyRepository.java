package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.pucrs.totem.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}