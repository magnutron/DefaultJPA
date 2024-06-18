package org.example.defaultjpa.service;

import org.example.defaultjpa.dto.ChildEntityDto;
import org.example.defaultjpa.entity.ChildEntity;
import org.example.defaultjpa.entity.ParentEntity;
import org.example.defaultjpa.repository.ChildEntityRepository;
import org.example.defaultjpa.repository.ParentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildEntityService {

    @Autowired
    private ChildEntityRepository childEntityRepository;

    @Autowired
    private ParentEntityRepository parentEntityRepository;

    public List<ChildEntityDto> findAllChildEntities() {
        return childEntityRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ChildEntityDto addChildEntity(ChildEntityDto childEntityDto) {
        ChildEntity childEntity = new ChildEntity();
        childEntity.setName(childEntityDto.getName());

        ParentEntity parentEntity = parentEntityRepository.findById(childEntityDto.getParentEntityId())
                .orElseThrow(() -> new RuntimeException("Parent entity not found"));
        childEntity.setParentEntity(parentEntity);

        childEntity = childEntityRepository.save(childEntity);
        return convertToDto(childEntity);
    }

    public void deleteChildEntity(Integer id) {
        childEntityRepository.deleteById(id);
    }

    private ChildEntityDto convertToDto(ChildEntity childEntity) {
        ChildEntityDto childEntityDto = new ChildEntityDto();
        childEntityDto.setId(childEntity.getId());
        childEntityDto.setName(childEntity.getName());
        childEntityDto.setParentEntityId(childEntity.getParentEntity().getId());
        return childEntityDto;
    }
}
