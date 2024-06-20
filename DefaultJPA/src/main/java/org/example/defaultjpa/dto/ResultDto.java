package org.example.defaultjpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResultDto {

    private Long id;
    private ParticipantDto participant;
    private DisciplineDto discipline;
    private String resultValue;
    private LocalDate date;
}
