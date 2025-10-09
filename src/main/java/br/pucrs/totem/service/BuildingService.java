package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.BuildingCompany;
import br.pucrs.totem.entity.BuildingEdge;
import br.pucrs.totem.entity.Company;
import br.pucrs.totem.entity.Node;
import br.pucrs.totem.entity.Edge;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.BuildingCompanyRepository;
import br.pucrs.totem.repository.BuildingEdgeRepository;
import br.pucrs.totem.repository.CompanyRepository;
import br.pucrs.totem.repository.EdgeRepository;
import br.pucrs.totem.repository.NodeRepository;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingCompanyRepository buildingCompanyRepository;
    private final BuildingEdgeRepository buildingEdgeRepository;
    private final CompanyRepository companyRepository;
    private final EdgeRepository edgeRepository;
    private final NodeRepository nodeRepository;

    public BuildingService(BuildingRepository buildingRepository, 
                          BuildingCompanyRepository buildingCompanyRepository,
                          BuildingEdgeRepository buildingEdgeRepository,
                          CompanyRepository companyRepository,
                          EdgeRepository edgeRepository,
                          NodeRepository nodeRepository) {
        this.buildingRepository = buildingRepository;
        this.buildingCompanyRepository = buildingCompanyRepository;
        this.buildingEdgeRepository = buildingEdgeRepository;
        this.companyRepository = companyRepository;
        this.edgeRepository = edgeRepository;
        this.nodeRepository = nodeRepository;
    }

    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    public Optional<Building> getBuildingById(Long id) {
        return buildingRepository.findById(id);
    }

    public Building saveBuilding(Building building) {
        return buildingRepository.save(building);
    }

    public Building updateBuilding(Long id, Building buildingDetails) {
        return buildingRepository.findById(id)
                .map(building -> {
                    building.setName(buildingDetails.getName());
                    building.setModelPath(buildingDetails.getModelPath());
                    building.setNode(buildingDetails.getNode());
                    return buildingRepository.save(building);
                })
                .orElse(null);
    }

    public boolean deleteBuilding(Long id) {
        if (buildingRepository.existsById(id)) {
            buildingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<BuildingCompany> getBuildingCompanies(Long buildingId) {
        return buildingCompanyRepository.findByBuildingId(buildingId);
    }

    public BuildingCompany addCompanyToBuilding(Long buildingId, Long companyId, String localization) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        Optional<Company> company = companyRepository.findById(companyId);
        if (building.isPresent() && company.isPresent()) {
            BuildingCompany buildingCompany = new BuildingCompany();
            buildingCompany.setBuilding(building.get());
            buildingCompany.setCompany(company.get());
            buildingCompany.setLocalization(localization);
            return buildingCompanyRepository.save(buildingCompany);
        }
        return null;
    }

    public BuildingCompany updateBuildingCompany(Long buildingCompanyId, String localization) {
        return buildingCompanyRepository.findById(buildingCompanyId)
                .map(buildingCompany -> {
                    buildingCompany.setLocalization(localization);
                    return buildingCompanyRepository.save(buildingCompany);
                })
                .orElse(null);
    }

    public boolean deleteBuildingCompany(Long buildingCompanyId) {
        if (buildingCompanyRepository.existsById(buildingCompanyId)) {
            buildingCompanyRepository.deleteById(buildingCompanyId);
            return true;
        }
        return false;
    }

    public List<BuildingEdge> getBuildingEdges(Long buildingId) {
        return buildingEdgeRepository.findByBuildingId(buildingId);
    }

    public BuildingEdge addEdgeToBuilding(Long buildingId, Long edgeId, Long nodeId) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        Optional<Edge> edge = edgeRepository.findById(edgeId);
        Optional<Node> node = nodeRepository.findById(nodeId);
        if (building.isPresent() && edge.isPresent() && node.isPresent()) {
            BuildingEdge buildingEdge = new BuildingEdge();
            buildingEdge.setBuilding(building.get());
            buildingEdge.setEdge(edge.get());
            buildingEdge.setNode(node.get());
            return buildingEdgeRepository.save(buildingEdge);
        }
        return null;
    }

    public BuildingEdge updateBuildingEdge(Long buildingEdgeId) {
        return buildingEdgeRepository.findById(buildingEdgeId)
                .map(buildingEdgeRepository::save)
                .orElse(null);
    }

    public boolean deleteBuildingEdge(Long buildingEdgeId) {
        if (buildingEdgeRepository.existsById(buildingEdgeId)) {
            buildingEdgeRepository.deleteById(buildingEdgeId);
            return true;
        }
        return false;
    }
}