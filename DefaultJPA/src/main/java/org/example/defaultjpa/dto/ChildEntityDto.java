package org.example.defaultjpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChildEntityDto {
    private int id;
    private String name;
    private int parentEntityId;
}
