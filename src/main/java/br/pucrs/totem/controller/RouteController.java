package br.pucrs.totem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.totem.service.RouteService;

@RestController
@RequestMapping("/routes")
public class RouteController {

    // @GetMapping("/map/{mapId}")
    // public List<RouteDto> getRoutesByMap(@PathVariable Long mapId) {
    //     return routeService.getRoutesByMap(mapId);
    // }
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }
    
    @GetMapping
    public ResponseEntity<List<Long>> getRoute(@RequestParam Long fromNodeId, @RequestParam Long toNodeId) {
        List<Long> path = this.routeService.findMinimumPath(fromNodeId, toNodeId);
        System.out.println(path);
        if (path == null || path.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(path);
        }
    }

    @GetMapping("/{fromBuildingId}")
    public ResponseEntity<List<Long>> getRouteByBuilding(@PathVariable Long fromBuildingId, @RequestParam Long toBuildingId) {
        List<Long> path = this.routeService.findMinimumPathToBuilding(fromBuildingId, toBuildingId);
        System.out.println(path);
        if (path == null || path.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(path);
        }
    }

    @GetMapping("/route/{id}")
    public ResponseEntity<Integer> getRouteById(@PathVariable Long id) {
        return ResponseEntity.ok(7);
    }
}