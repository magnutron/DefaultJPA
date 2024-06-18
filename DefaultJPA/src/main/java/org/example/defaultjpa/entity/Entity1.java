package org.example.defaultjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "my_entities1")
public class Entity1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "entity1", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Entity2> myEntities2 = new HashSet<>();
}
