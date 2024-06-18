package org.example.defaultjpa.service;

import jakarta.transaction.Transactional;
import org.example.defaultjpa.dto.Entity1Dto;
import org.example.defaultjpa.entity.Entity1;
import org.springframework.stereotype.Service;
import org.example.defaultjpa.repository.Entity1Repository;
import org.example.defaultjpa.repository.Entity2Repository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Entity1Service {

    private Entity1Repository entity1Repository;
    private Entity2Repository entity2Repository;

    public Entity1Service (Entity1Repository entity1Repository, Entity2Repository entity2Repository) {
        this.entity1Repository = entity1Repository;
        this.entity2Repository = entity2Repository;
    }

    public List<Entity1Dto> findAllEntity1() {
        List<Entity1> entity1List = entity1Repository.findAll();
        return entity1List.stream()
                .map(entity1 -> new Entity1Dto(entity1.getId(), entity1.getName()))
                .collect(Collectors.toList());
    }

    public Entity1Dto findEntity1ById(Integer id) {
        Entity1 entity1 = entity1Repository.findById(id).orElseThrow();
        return new Entity1Dto(entity1.getId(), entity1.getName());
    }

    public Entity1Dto addEntity1(Entity1Dto request) {
        Entity1 entity1 = new Entity1();
        entity1.setName(request.getName());
        // Probably have an updateEntity method here if it is more than just setting a name and such
        entity1Repository.save(entity1);
        return new Entity1Dto(entity1.getId(), entity1.getName());
    }

    public Entity1Dto updateEntity1(Integer id, Entity1Dto request) {
        Entity1 entity1 = entity1Repository.findById(id).orElseThrow();
        entity1.setName(request.getName());
        // Probably have an updateEntity method here if it is more than just setting a name and such
        entity1Repository.save(entity1);
        return new Entity1Dto(entity1.getId(), entity1.getName());
    }

    @Transactional
    public void deleteEntity1(Integer id) {
        entity2Repository.deleteByEntity1Id(id);
        entity1Repository.deleteById(id);
    }
}
