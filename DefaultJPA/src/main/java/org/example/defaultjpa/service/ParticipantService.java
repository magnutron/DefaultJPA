package org.example.defaultjpa.service;

import org.example.defaultjpa.dto.*;
import org.example.defaultjpa.entity.Discipline;
import org.example.defaultjpa.entity.Participant;
import org.example.defaultjpa.entity.Result;
import org.example.defaultjpa.exception.ResourceNotFoundException;
import org.example.defaultjpa.repository.DisciplineRepository;
import org.example.defaultjpa.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<ParticipantDto> getAllParticipants() {
        return participantRepository.findAll()
                .stream()
                .map(this::convertParticipantToDto)
                .collect(Collectors.toList());
    }

    public ParticipantDto getParticipantById(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found"));
        return convertParticipantToDto(participant);
    }

    public ParticipantDto createParticipant(ParticipantDto participantDto) {
        Participant participant = convertParticipantToEntity(participantDto);
        Participant savedParticipant = participantRepository.save(participant);
        return convertParticipantToDto(savedParticipant);
    }

    public ParticipantDto updateParticipant(Long id, ParticipantDto participantDto) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found"));

        participant.setName(participantDto.getName());
        participant.setGender(participantDto.getGender());
        participant.setDateOfBirth(participantDto.getDateOfBirth());
        participant.setClub(participantDto.getClub());
        participant.setDisciplines(participantDto.getDisciplines().stream()
                .map(this::convertDisciplineToEntity)
                .collect(Collectors.toSet()));
        participant.setResults(participantDto.getResults() != null ? participantDto.getResults().stream()
                .map(this::convertResultToEntity)
                .collect(Collectors.toSet()) : new HashSet<>());

        Participant updatedParticipant = participantRepository.save(participant);
        return convertParticipantToDto(updatedParticipant);
    }

    public void deleteParticipant(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found"));
        participantRepository.delete(participant);
    }

    private ParticipantDto convertParticipantToDto(Participant participant) {
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setId(participant.getId());
        participantDto.setName(participant.getName());
        participantDto.setGender(participant.getGender());
        participantDto.setDateOfBirth(participant.getDateOfBirth());
        participantDto.setClub(participant.getClub());
        participantDto.setDisciplines(participant.getDisciplines().stream()
                .map(this::convertDisciplineToDto)
                .collect(Collectors.toSet()));
        participantDto.setResults(participant.getResults().stream()
                .map(this::convertResultToDto)
                .collect(Collectors.toSet()));
        return participantDto;
    }

    private Participant convertParticipantToEntity(ParticipantDto participantDto) {
        Participant participant = new Participant();
        participant.setId(participantDto.getId());
        participant.setName(participantDto.getName());
        participant.setGender(participantDto.getGender());
        participant.setDateOfBirth(participantDto.getDateOfBirth());
        participant.setClub(participantDto.getClub());
        participant.setDisciplines(participantDto.getDisciplines() != null ? participantDto.getDisciplines().stream()
                .map(this::convertDisciplineToEntity)
                .collect(Collectors.toSet()) : new HashSet<>());
        participant.setResults(participantDto.getResults() != null ? participantDto.getResults().stream()
                .map(this::convertResultToEntity)
                .collect(Collectors.toSet()) : new HashSet<>());
        return participant;
    }

    private DisciplineDto convertDisciplineToDto(Discipline discipline) {
        DisciplineDto disciplineDto = new DisciplineDto();
        disciplineDto.setId(discipline.getId());
        disciplineDto.setName(discipline.getName());
        disciplineDto.setResultType(discipline.getResultType());
        return disciplineDto;
    }

    private Discipline convertDisciplineToEntity(DisciplineDto disciplineDto) {
        Discipline discipline = new Discipline();
        discipline.setId(disciplineDto.getId());
        discipline.setName(disciplineDto.getName());
        discipline.setResultType(disciplineDto.getResultType());
        return discipline;
    }

    private ResultDto convertResultToDto(Result result) {
        ResultDto resultDto = new ResultDto();
        resultDto.setId(result.getId());
        resultDto.setResultValue(result.getResultValue());
        resultDto.setDate(result.getDate());
        resultDto.setDiscipline(convertDisciplineToDto(result.getDiscipline()));
        resultDto.setParticipant(convertParticipantToDto(result.getParticipant()));
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
}
