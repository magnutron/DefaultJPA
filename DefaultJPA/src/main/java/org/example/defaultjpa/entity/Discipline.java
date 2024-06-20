package org.example.defaultjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String resultType;

    @OneToMany(mappedBy = "discipline")
    private Set<Result> results;

    @ManyToMany(mappedBy = "disciplines")
    private Set<Participant> participants;
}
