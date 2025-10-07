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

    public List<Coordinate> getAllCoordinates() {
        return coordinateRepository.findAll();
    }

    public Optional<Coordinate> getCoordinateById(Long id) {
        return coordinateRepository.findById(id);
    }

    public Coordinate saveCoordinate(Coordinate coordinate) {
        return coordinateRepository.save(coordinate);
    }

    public Coordinate updateCoordinate(Long id, Coordinate coordinateDetails) {
        return coordinateRepository.findById(id)
                .map(coordinate -> {
                    coordinate.setX(coordinateDetails.getX());
                    coordinate.setY(coordinateDetails.getY());
                    return coordinateRepository.save(coordinate);
                })
                .orElse(null);
    }

    public boolean deleteCoordinate(Long id) {
        if (coordinateRepository.existsById(id)) {
            coordinateRepository.deleteById(id);
            return true;
        }
        return false;
    }
}