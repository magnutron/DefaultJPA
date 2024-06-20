package org.example.defaultjpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ParticipantDto {
    private Long id;
    private String name;
    private String gender;
    private String dateOfBirth;
    private String club;
    private Set<DisciplineDto> disciplines = new HashSet<>();
    private Set<ResultDto> results = new HashSet<>();

}
