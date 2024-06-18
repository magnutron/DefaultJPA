package org.example.defaultjpa.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Entity1Dto {
    private int id;
    private String name;

    public Entity1Dto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
