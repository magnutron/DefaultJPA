package org.example.defaultjpa.controller;

import org.example.defaultjpa.dto.ParentEntityDto;
import org.example.defaultjpa.service.ParentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parent-entities")
public class ParentEntityController {

    @Autowired
    private ParentEntityService parentEntityService;

    @GetMapping
    public List<ParentEntityDto> findAllParentEntities() {
        return parentEntityService.findAllParentEntities();
    }

    @PostMapping
    public ParentEntityDto addParentEntity(@RequestBody ParentEntityDto parentEntityDto) {
        return parentEntityService.addParentEntity(parentEntityDto);
    }

    @DeleteMapping("/{id}")
    public void deleteParentEntity(@PathVariable Integer id) {
        parentEntityService.deleteParentEntity(id);
    }

    @PutMapping("/{id}")
    public ParentEntityDto updateParentEntity(@PathVariable Integer id, @RequestBody ParentEntityDto parentEntityDto) {
        return parentEntityService.updateParentEntity(id, parentEntityDto);
    }
}
