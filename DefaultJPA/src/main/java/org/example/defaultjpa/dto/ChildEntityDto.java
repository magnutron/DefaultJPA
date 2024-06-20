package org.example.defaultjpa.dto;

public class ChildEntityDto {
    private int id;
    private String name;
    private int parentEntityId;

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

    public int getParentEntityId() {
        return parentEntityId;
    }

    public void setParentEntityId(int parentEntityId) {
        this.parentEntityId = parentEntityId;
    }
}
