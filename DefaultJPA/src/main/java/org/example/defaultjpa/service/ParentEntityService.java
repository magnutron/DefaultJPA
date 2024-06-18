package org.example.defaultjpa.service;

import org.example.defaultjpa.dto.ChildEntityDto;
import org.example.defaultjpa.dto.ParentEntityDto;
import org.example.defaultjpa.entity.ChildEntity;
import org.example.defaultjpa.entity.ParentEntity;
import org.example.defaultjpa.repository.ParentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParentEntityService {

    @Autowired
    private ParentEntityRepository parentEntityRepository;

    public List<ParentEntityDto> findAllParentEntities() {
        return parentEntityRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ParentEntityDto addParentEntity(ParentEntityDto parentEntityDto) {
        ParentEntity parentEntity = new ParentEntity();
        parentEntity.setName(parentEntityDto.getName());
        // Add logic to convert and set child entities if needed

        parentEntity = parentEntityRepository.save(parentEntity);
        return convertToDto(parentEntity);
    }

    public void deleteParentEntity(Integer id) {
        parentEntityRepository.deleteById(id);
    }

    private ParentEntityDto convertToDto(ParentEntity parentEntity) {
        ParentEntityDto parentEntityDto = new ParentEntityDto();
        parentEntityDto.setId(parentEntity.getId());
        parentEntityDto.setName(parentEntity.getName());
        parentEntityDto.setChildEntities(convertChildEntitiesToDto(parentEntity.getChildEntities()));
        return parentEntityDto;
    }

    private Set<ChildEntityDto> convertChildEntitiesToDto(Set<ChildEntity> childEntities) {
        return childEntities.stream().map(this::convertChildEntityToDto).collect(Collectors.toSet());
    }

    private ChildEntityDto convertChildEntityToDto(ChildEntity childEntity) {
        ChildEntityDto childEntityDto = new ChildEntityDto();
        childEntityDto.setId(childEntity.getId());
        childEntityDto.setName(childEntity.getName());
        return childEntityDto;
    }
}
