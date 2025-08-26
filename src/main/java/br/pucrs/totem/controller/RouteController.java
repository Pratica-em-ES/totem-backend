package br.pucrs.totem.controller;

import br.pucrs.totem.dto.RouteDto;
import br.pucrs.totem.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/map/{mapId}")
    public List<RouteDto> getRoutesByMap(@PathVariable Long mapId) {
        return routeService.getRoutesByMap(mapId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}