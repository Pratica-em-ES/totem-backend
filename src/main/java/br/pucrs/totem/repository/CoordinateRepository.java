package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.pucrs.totem.entity.Coordinate;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
}