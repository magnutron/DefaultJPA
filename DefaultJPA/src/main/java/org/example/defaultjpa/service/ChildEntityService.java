package org.example.defaultjpa.service;

import org.example.defaultjpa.dto.ChildEntityDto;
import org.example.defaultjpa.entity.ChildEntity;
import org.example.defaultjpa.entity.ParentEntity;
import org.example.defaultjpa.repository.ChildEntityRepository;
import org.example.defaultjpa.repository.ParentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChildEntityService {

    @Autowired
    private ChildEntityRepository childEntityRepository;

    @Autowired
    private ParentEntityRepository parentEntityRepository;

    public Set<ChildEntityDto> findAllChildEntities() {
        return childEntityRepository.findAll().stream()
                .map(this::convertChildEntityToDto)
                .collect(Collectors.toSet());
    }

    public Set<ChildEntityDto> findAllChildEntitiesByParentId(Integer parentId) {
        ParentEntity parentEntity = parentEntityRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent entity not found"));
        return parentEntity.getChildEntities().stream()
                .map(this::convertChildEntityToDto)
                .collect(Collectors.toSet());
    }

    public ChildEntityDto addChildEntity(Integer parentId, ChildEntityDto childEntityDto) {
        ParentEntity parentEntity = parentEntityRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent entity not found"));
        ChildEntity childEntity = new ChildEntity();
        childEntity.setName(childEntityDto.getName());
        childEntity.setParentEntity(parentEntity);

        // Save the child entity and update the parent entity's children set
        childEntity = childEntityRepository.save(childEntity);
        parentEntity.getChildEntities().add(childEntity);
        parentEntity = parentEntityRepository.save(parentEntity);

        return convertChildEntityToDto(childEntity);
    }

    public void deleteChildEntity(Integer parentId, Integer childId) {
        ParentEntity parentEntity = parentEntityRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent entity not found"));
        parentEntity.getChildEntities().removeIf(childEntity -> childEntity.getId() == childId);
        parentEntityRepository.save(parentEntity);
    }

    public ChildEntityDto updateChildEntity(Integer parentId, Integer childId, ChildEntityDto childEntityDto) {
        ParentEntity parentEntity = parentEntityRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent entity not found"));
        ChildEntity childEntity = parentEntity.getChildEntities().stream()
                .filter(ce -> ce.getId() == childId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Child entity not found"));

        childEntity.setName(childEntityDto.getName());
        parentEntity = parentEntityRepository.save(parentEntity);
        return convertChildEntityToDto(childEntity);
    }

    private ChildEntityDto convertChildEntityToDto(ChildEntity childEntity) {
        ChildEntityDto childEntityDto = new ChildEntityDto();
        childEntityDto.setId(childEntity.getId());
        childEntityDto.setName(childEntity.getName());
        childEntityDto.setParentEntityId(childEntity.getParentEntity().getId());
        return childEntityDto;
    }
}
