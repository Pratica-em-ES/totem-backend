package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucrs.totem.entity.Coordinate;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
}