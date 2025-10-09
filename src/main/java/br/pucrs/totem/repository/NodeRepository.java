package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.pucrs.totem.entity.Node;

public interface NodeRepository extends JpaRepository<Node, Long> {
}
