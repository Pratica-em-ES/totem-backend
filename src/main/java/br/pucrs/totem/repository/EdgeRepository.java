package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.pucrs.totem.entity.Edge;

public interface EdgeRepository extends JpaRepository<Edge, Long> {
}
