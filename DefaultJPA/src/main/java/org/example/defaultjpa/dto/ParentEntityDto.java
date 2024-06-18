package org.example.defaultjpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ParentEntityDto {
    private int id;
    private String name;
    private Set<ChildEntityDto> childEntities;
}
