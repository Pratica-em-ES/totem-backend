package br.pucrs.totem.controller;

import br.pucrs.totem.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    // @Autowired
    // private SearchService searchService;

    // @GetMapping
    // public List<MapEntity> searchMaps(@RequestParam String query) {
    //     return searchService.searchMapsByName(query);
    // }
}