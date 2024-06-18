package org.example.defaultjpa.api;

import org.example.defaultjpa.dto.Entity1Dto;
import org.example.defaultjpa.service.Entity1Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entity1")
public class Entity1Controller {

    private Entity1Service entity1Service;

    public Entity1Controller(Entity1Service entity1Service) {
        this.entity1Service = entity1Service;
    }

    @GetMapping
    public List<Entity1Dto> findAllEntity1() {
        return entity1Service.findAllEntity1();
    }

    @GetMapping("/{id}")
    public Entity1Dto findEntity1ById(@PathVariable Integer id) {
        return entity1Service.findEntity1ById(id);
    }

    @PostMapping
        public Entity1Dto addEntity1(@RequestBody Entity1Dto request) {
        return entity1Service.addEntity1(request);
    }

    @PutMapping("/{id}")
    public Entity1Dto updateEntity1(@PathVariable Integer id, @RequestBody Entity1Dto request) {
        return entity1Service.updateEntity1(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteEntity1(@PathVariable Integer id) {
        entity1Service.deleteEntity1(id);
    }
}
