package org.example.defaultjpa.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.defaultjpa.enums.Gender;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ParticipantDto {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    @NotNull(message = "Date of birth is mandatory")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Club is mandatory")
    private String club;

    private Set<DisciplineDto> disciplines;

    private Set<ResultDto> results;
}
