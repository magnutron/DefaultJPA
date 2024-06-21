package org.example.defaultjpa.service;

import org.example.defaultjpa.dto.DisciplineDto;
import org.example.defaultjpa.dto.ParticipantDto;
import org.example.defaultjpa.dto.ResultDto;
import org.example.defaultjpa.entity.Participant;
import org.example.defaultjpa.enums.Gender;
import org.example.defaultjpa.entity.Result;
import org.example.defaultjpa.repository.DisciplineRepository;
import org.example.defaultjpa.repository.ParticipantRepository;
import org.example.defaultjpa.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private ResultRepository resultRepository;

    public List<ParticipantDto> getAllParticipants() {
        return participantRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ParticipantDto getParticipantById(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid participant ID: " + id));
        return convertToDto(participant);
    }

    public ParticipantDto createParticipant(ParticipantDto participantDto) {
        Participant participant = convertToEntity(participantDto);
        participant = participantRepository.save(participant);
        return convertToDto(participant);
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }

    public ParticipantDto updateParticipant(Long id, ParticipantDto participantDto) {
        Participant participant = convertToEntity(participantDto);
        participant.setId(id);
        participant = participantRepository.save(participant);
        return convertToDto(participant);
    }



    private Participant convertToEntity(ParticipantDto participantDto) {
        Participant participant = new Participant();
        participant.setName(participantDto.getName());
        participant.setGender(Gender.valueOf(participantDto.getGender()));
        participant.setDateOfBirth(LocalDate.parse(participantDto.getDateOfBirth()));
        participant.setClub(participantDto.getClub());

        if (participantDto.getDisciplines() != null) {
            participant.setDisciplines(participantDto.getDisciplines().stream()
                    .map(disciplineDto -> disciplineRepository.findById(disciplineDto.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid discipline ID: " + disciplineDto.getId())))
                    .collect(Collectors.toSet()));
        }

        if (participantDto.getResults() != null) {
            participant.setResults(participantDto.getResults().stream()
                    .map(resultDto -> {
                        Result result = new Result();
                        result.setDiscipline(disciplineRepository.findById(resultDto.getDiscipline().getId())
                                .orElseThrow(() -> new IllegalArgumentException("Invalid discipline ID: " + resultDto.getDiscipline().getId())));
                        result.setResultValue(resultDto.getResultValue());
                        result.setDate(resultDto.getDate());
                        result.setParticipant(participant);
                        return result;
                    })
                    .collect(Collectors.toSet()));
        }

        return participant;
    }

    private ParticipantDto convertToDto(Participant participant) {
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setId(participant.getId());
        participantDto.setName(participant.getName());
        participantDto.setGender(participant.getGender().toString());
        participantDto.setDateOfBirth(participant.getDateOfBirth().toString());
        participantDto.setClub(participant.getClub());

        if (participant.getDisciplines() != null) {
            participantDto.setDisciplines(participant.getDisciplines().stream()
                    .map(discipline -> {
                        DisciplineDto disciplineDto = new DisciplineDto();
                        disciplineDto.setId(discipline.getId());
                        disciplineDto.setName(discipline.getName());
                        disciplineDto.setResultType(discipline.getResultType());
                        return disciplineDto;
                    })
                    .collect(Collectors.toSet()));
        }

        if (participant.getResults() != null) {
            participantDto.setResults(participant.getResults().stream()
                    .map(result -> {
                        ResultDto resultDto = new ResultDto();
                        resultDto.setId(result.getId());
                        resultDto.setResultValue(result.getResultValue());
                        resultDto.setDate(result.getDate());
                        return resultDto;
                    })
                    .collect(Collectors.toSet()));
        }
        return participantDto;
    }
}
