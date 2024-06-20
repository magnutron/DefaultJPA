package org.example.defaultjpa.service;

import org.example.defaultjpa.dto.DisciplineDto;
import org.example.defaultjpa.dto.ParticipantDto;
import org.example.defaultjpa.dto.ResultDto;
import org.example.defaultjpa.entity.Discipline;
import org.example.defaultjpa.entity.Participant;
import org.example.defaultjpa.entity.Result;
import org.example.defaultjpa.repository.DisciplineRepository;
import org.example.defaultjpa.repository.ParticipantRepository;
import org.example.defaultjpa.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<ResultDto> getAllResults() {
        return resultRepository.findAll().stream()
                .map(this::convertResultToDto)
                .collect(Collectors.toList());
    }

    public ResultDto getResultById(Long id) {
        return resultRepository.findById(id)
                .map(this::convertResultToDto)
                .orElseThrow(() -> new IllegalArgumentException("Result not found with id: " + id));
    }

    public ResultDto createResult(ResultDto resultDto) {
        Result result = convertResultToEntity(resultDto);
        Result savedResult = resultRepository.save(result);
        return convertResultToDto(savedResult);
    }

    public ResultDto updateResult(Long id, ResultDto resultDto) {
        Result existingResult = resultRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Result not found with id: " + id));

        existingResult.setResultValue(resultDto.getResultValue());
        existingResult.setDate(resultDto.getDate());

        if (resultDto.getParticipant() != null) {
            Participant participant = participantRepository.findById(resultDto.getParticipant().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid participant ID"));
            existingResult.setParticipant(participant);
        }

        if (resultDto.getDiscipline() != null) {
            Discipline discipline = disciplineRepository.findById(resultDto.getDiscipline().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid discipline ID"));
            existingResult.setDiscipline(discipline);
        }

        Result updatedResult = resultRepository.save(existingResult);
        return convertResultToDto(updatedResult);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    private Result convertResultToEntity(ResultDto resultDto) {
        Result result = new Result();
        result.setId(resultDto.getId());
        result.setResultValue(resultDto.getResultValue());

        // Convert String to LocalDate
        result.setDate(LocalDate.parse(resultDto.getDate().toString(), dateFormatter));

        if (resultDto.getParticipant() != null) {
            Participant participant = participantRepository.findById(resultDto.getParticipant().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid participant ID"));
            result.setParticipant(participant);
        }

        if (resultDto.getDiscipline() != null) {
            Discipline discipline = disciplineRepository.findById(resultDto.getDiscipline().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid discipline ID"));
            result.setDiscipline(discipline);
        }

        return result;
    }

    private ResultDto convertResultToDto(Result result) {
        ResultDto resultDto = new ResultDto();
        resultDto.setId(result.getId());
        resultDto.setResultValue(result.getResultValue());
        resultDto.setDate(result.getDate());


        if (result.getParticipant() != null) {
            ParticipantDto participantDto = new ParticipantDto();
            participantDto.setId(result.getParticipant().getId());
            participantDto.setName(result.getParticipant().getName());
            participantDto.setGender(result.getParticipant().getGender().toString());
            participantDto.setDateOfBirth(result.getParticipant().getDateOfBirth().toString());
            participantDto.setClub(result.getParticipant().getClub());
            resultDto.setParticipant(participantDto);
        }

        if (result.getDiscipline() != null) {
            DisciplineDto disciplineDto = new DisciplineDto();
            disciplineDto.setId(result.getDiscipline().getId());
            resultDto.setDiscipline(disciplineDto);
        }

        return resultDto;


    }
}
