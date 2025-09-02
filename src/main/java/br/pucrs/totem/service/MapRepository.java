package br.pucrs.totem.service;

import br.pucrs.totem.entity.Map;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<Map, UUID> {}