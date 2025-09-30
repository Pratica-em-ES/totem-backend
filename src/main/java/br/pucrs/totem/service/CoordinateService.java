package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Coordinate;
import br.pucrs.totem.repository.CoordinateRepository;

@Service
public class CoordinateService {

    private final CoordinateRepository coordinateRepository;

    public CoordinateService(CoordinateRepository coordinateRepository) {
        this.coordinateRepository = coordinateRepository;
    }

    public List<Coordinate> findAll() {
        return coordinateRepository.findAll();
    }

    public Optional<Coordinate> findById(Long id) {
        return coordinateRepository.findById(id);
    }

    public Coordinate save(Coordinate coordinate) {
        return coordinateRepository.save(coordinate);
    }

    public void deleteById(Long id) {
        coordinateRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return coordinateRepository.existsById(id);
    }
}