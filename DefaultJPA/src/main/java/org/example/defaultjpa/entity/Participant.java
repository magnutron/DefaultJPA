package org.example.defaultjpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.defaultjpa.enums.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Gender is mandatory")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Date of birth is mandatory")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Club is mandatory")
    private String club;

    @ManyToMany
    @JoinTable(
            name = "participant_discipline",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    @JsonManagedReference
    private Set<Discipline> disciplines;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Result> results;
}
