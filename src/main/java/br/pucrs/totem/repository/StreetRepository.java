package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucrs.totem.entity.Street;

import java.util.List;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
}