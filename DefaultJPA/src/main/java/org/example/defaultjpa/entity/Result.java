package org.example.defaultjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    @JsonBackReference
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    @JsonBackReference
    private Discipline discipline;

    private String resultValue;
    private LocalDate date;
}
