package org.example.defaultjpa.dto;

import java.util.Set;

public class ParentEntityDto {
    private int id;
    private String name;
    private Set<ChildEntityDto> childEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ChildEntityDto> getChildEntities() {
        return childEntities;
    }

    public void setChildEntities(Set<ChildEntityDto> childEntities) {
        this.childEntities = childEntities;
    }
}
