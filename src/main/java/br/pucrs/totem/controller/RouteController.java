package br.pucrs.totem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<List<Long>> getRouteById(@PathVariable Long id) {

        List<Long> path = this.routeService.findMinimumPathToBuilding(2L, id);
        System.out.println(path);
        if (path == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(path);
        }
    }

    @GetMapping("/route/{id}")
    public ResponseEntity<Integer> getRoute(@PathVariable Long id) {

        return ResponseEntity.ok(7);
    }
}