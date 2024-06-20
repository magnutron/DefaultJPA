package org.example.defaultjpa.service;

import org.example.defaultjpa.dto.DisciplineDto;
import org.example.defaultjpa.dto.ParticipantDto;
import org.example.defaultjpa.dto.ResultDto;
import org.example.defaultjpa.entity.Discipline;
import org.example.defaultjpa.entity.Participant;
import org.example.defaultjpa.entity.Result;
import org.example.defaultjpa.exception.ResourceNotFoundException;
import org.example.defaultjpa.repository.DisciplineRepository;
import org.example.defaultjpa.repository.ParticipantRepository;
import org.example.defaultjpa.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<ResultDto> getAllResults() {
        return resultRepository.findAll()
                .stream()
                .map(this::convertResultToDto)
                .collect(Collectors.toList());
    }

    public ResultDto getResultById(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
        return convertResultToDto(result);
    }

    public ResultDto createResult(ResultDto resultDto) {
        Result result = new Result();
        result.setResultValue(resultDto.getResultValue());
        result.setDate(resultDto.getDate());

        Participant participant = participantRepository.findById(resultDto.getParticipant().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found"));
        result.setParticipant(participant);

        Discipline discipline = disciplineRepository.findById(resultDto.getDiscipline().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found"));
        result.setDiscipline(discipline);

        Result savedResult = resultRepository.save(result);
        return convertResultToDto(savedResult);
    }

    public ResultDto updateResult(Long id, ResultDto resultDto) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));

        result.setResultValue(resultDto.getResultValue());
        result.setDate(resultDto.getDate());
        result.setDiscipline(disciplineRepository.findById(resultDto.getDiscipline().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found")));
        result.setParticipant(participantRepository.findById(resultDto.getParticipant().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found")));

        Result updatedResult = resultRepository.save(result);
        return convertResultToDto(updatedResult);
    }

    public void deleteResult(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
        resultRepository.delete(result);
    }

    private ResultDto convertResultToDto(Result result) {
        ResultDto resultDto = new ResultDto();
        resultDto.setId(result.getId());
        resultDto.setResultValue(result.getResultValue());
        resultDto.setDate(result.getDate());
        resultDto.setParticipant(convertParticipantToDto(result.getParticipant()));
        resultDto.setDiscipline(convertDisciplineToDto(result.getDiscipline()));
        return resultDto;
    }

    private Result convertResultToEntity(ResultDto resultDto) {
        Result result = new Result();
        result.setId(resultDto.getId());
        result.setResultValue(resultDto.getResultValue());
        result.setDate(resultDto.getDate());
        result.setDiscipline(disciplineRepository.findById(resultDto.getDiscipline().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found")));
        result.setParticipant(participantRepository.findById(resultDto.getParticipant().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found")));
        return result;
    }

    private DisciplineDto convertDisciplineToDto(Discipline discipline) {
        DisciplineDto disciplineDto = new DisciplineDto();
        disciplineDto.setId(discipline.getId());
        disciplineDto.setName(discipline.getName());
        disciplineDto.setResultType(discipline.getResultType());
        return disciplineDto;
    }

    private ParticipantDto convertParticipantToDto(Participant participant) {
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setId(participant.getId());
        participantDto.setName(participant.getName());
        participantDto.setGender(participant.getGender());
        participantDto.setDateOfBirth(participant.getDateOfBirth());
        participantDto.setClub(participant.getClub());
        return participantDto;
    }
}
