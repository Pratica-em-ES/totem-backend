package br.pucrs.totem;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/totem")
public class TotemController {

    // Exemplo de injeção de serviço (adicione os serviços reais conforme necessário)
    // @Autowired
    // @Autowired
    // private ReportService reportService;
    // @Autowired
    // private ProductService productService;
    // @Autowired
    // private MapService mapService;
    // @Autowired
    // private SearchService searchService;

    // --- Reports ---
    @PostMapping("/reports")
    public String createReport(@RequestBody Object createReportDto) {
        // return reportService.create(createReportDto);
        return "createReport";
    }

    // --- Route ---
    @PostMapping("/routes")
    public String createRoute(@RequestBody Object createRouteDto) {
        // return routeService.create(createRouteDto);
        return "createRoute";
    }

    // --- Maps ---
    @PostMapping("/maps")
    public String createMap(@RequestBody Object createMapDto) {
        // return mapService.create(createMapDto);
        return "createMap";
    }

    @GetMapping("/maps")
    public String findAllMaps() {
        // return mapService.findAll();
        return "findAllMaps";
    }

    @GetMapping("/maps/{id}")
    public String findMap(@PathVariable Long id) {
        // return mapService.findOne(id);
        return "findMap " + id;
    }

    @PatchMapping("/maps/{id}")
    public String updateMap(@PathVariable Long id, @RequestBody Object updateMapDto) {
        // return mapService.update(id, updateMapDto);
        return "updateMap " + id;
    }

    @DeleteMapping("/maps/{id}")
    public String removeMap(@PathVariable Long id) {
        // return mapService.remove(id);
        return "removeMap " + id;
    }

    // --- Search ---
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String name) {
        // return searchService.searchExhibitors(name);
        return "search " + name;
    }


}
