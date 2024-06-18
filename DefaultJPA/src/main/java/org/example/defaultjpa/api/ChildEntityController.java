package org.example.defaultjpa.api;

import org.example.defaultjpa.dto.ChildEntityDto;
import org.example.defaultjpa.service.ChildEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/child-entities")
public class ChildEntityController {

    @Autowired
    private ChildEntityService childEntityService;

    @GetMapping
    public List<ChildEntityDto> findAllChildEntities() {
        return childEntityService.findAllChildEntities();
    }

    @PostMapping
    public ChildEntityDto addChildEntity(@RequestBody ChildEntityDto childEntityDto) {
        return childEntityService.addChildEntity(childEntityDto);
    }

    @DeleteMapping("/{id}")
    public void deleteChildEntity(@PathVariable Integer id) {
        childEntityService.deleteChildEntity(id);
    }
}
