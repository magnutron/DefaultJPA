package org.example.defaultjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @JsonBackReference
    private Set<Result> results;

    @ManyToMany(mappedBy = "disciplines")
    @JsonBackReference
    private Set<Participant> participants;
}
