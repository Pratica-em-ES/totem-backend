package br.pucrs.totem.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteController {

    // @GetMapping("/map/{mapId}")
    // public List<RouteDto> getRoutesByMap(@PathVariable Long mapId) {
    //     return routeService.getRoutesByMap(mapId);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<RouteDto> getRouteById(@PathVariable Long id) {
    //     return routeService.getRouteById(id)
    //             .map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }
}