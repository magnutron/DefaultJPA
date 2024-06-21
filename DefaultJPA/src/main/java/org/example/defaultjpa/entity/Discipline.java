package org.example.defaultjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.example.defaultjpa.enums.SortingDirection;

import java.util.HashSet;
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

    @Enumerated(EnumType.STRING)
    private SortingDirection sortingDirection; // Add this line

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Result> results = new HashSet<>();

    @ManyToMany(mappedBy = "disciplines")
    @JsonBackReference
    private Set<Participant> participants;
}
