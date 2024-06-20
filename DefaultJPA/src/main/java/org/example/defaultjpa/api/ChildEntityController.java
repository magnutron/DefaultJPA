package org.example.defaultjpa.controller;

import org.example.defaultjpa.dto.ChildEntityDto;
import org.example.defaultjpa.service.ChildEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/child-entities")
public class ChildEntityController {

    @Autowired
    private ChildEntityService childEntityService;

    @GetMapping
    public Set<ChildEntityDto> findAllChildEntities() {
        return childEntityService.findAllChildEntities();
    }

    @GetMapping("/{parentId}")
    public Set<ChildEntityDto> findAllChildEntitiesByParentId(@PathVariable Integer parentId) {
        return childEntityService.findAllChildEntitiesByParentId(parentId);
    }

    @PostMapping("/{parentId}")
    public ChildEntityDto addChildEntity(@PathVariable Integer parentId, @RequestBody ChildEntityDto childEntityDto) {
        return childEntityService.addChildEntity(parentId, childEntityDto);
    }

    @DeleteMapping("/{parentId}/{childId}")
    public void deleteChildEntity(@PathVariable Integer parentId, @PathVariable Integer childId) {
        childEntityService.deleteChildEntity(parentId, childId);
    }

    @PutMapping("/{parentId}/{childId}")
    public ChildEntityDto updateChildEntity(@PathVariable Integer parentId, @PathVariable Integer childId, @RequestBody ChildEntityDto childEntityDto) {
        return childEntityService.updateChildEntity(parentId, childId, childEntityDto);
    }
}
