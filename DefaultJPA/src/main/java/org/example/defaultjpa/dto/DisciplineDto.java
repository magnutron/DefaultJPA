package org.example.defaultjpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DisciplineDto {

    private Long id;
    private String name;
    private String resultType;
    private String sortingDirection;

}
