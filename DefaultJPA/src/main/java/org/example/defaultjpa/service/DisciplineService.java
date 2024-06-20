package org.example.defaultjpa.service;


import org.example.defaultjpa.dto.DisciplineDto;
import org.example.defaultjpa.entity.Discipline;
import org.example.defaultjpa.entity.Participant;
import org.example.defaultjpa.exception.ResourceNotFoundException;
import org.example.defaultjpa.repository.DisciplineRepository;
import org.example.defaultjpa.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    public List<DisciplineDto> getAllDisciplines() {
        return disciplineRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public DisciplineDto getDisciplineById(Long id) {
        Discipline discipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found"));
        return convertToDto(discipline);
    }

    public DisciplineDto createDiscipline(DisciplineDto disciplineDto) {
        Discipline discipline = convertToEntity(disciplineDto);
        Discipline savedDiscipline = disciplineRepository.save(discipline);
        return convertToDto(savedDiscipline);
    }

    public DisciplineDto updateDiscipline(Long id, DisciplineDto disciplineDto) {
        Discipline discipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found"));

        discipline.setName(disciplineDto.getName());
        discipline.setResultType(disciplineDto.getResultType());

        Discipline updatedDiscipline = disciplineRepository.save(discipline);
        return convertToDto(updatedDiscipline);
    }

    public void deleteDiscipline(Long id) {
        Discipline discipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found"));

        // Remove the discipline from all participants
        List<Participant> participants = participantRepository.findAll();
        for (Participant participant : participants) {
            participant.getDisciplines().remove(discipline);
            participantRepository.save(participant);
        }

        disciplineRepository.delete(discipline);
    }

    private DisciplineDto convertToDto(Discipline discipline) {
        DisciplineDto disciplineDto = new DisciplineDto();
        disciplineDto.setId(discipline.getId());
        disciplineDto.setName(discipline.getName());
        disciplineDto.setResultType(discipline.getResultType());
        return disciplineDto;
    }

    private Discipline convertToEntity(DisciplineDto disciplineDto) {
        Discipline discipline = new Discipline();
        discipline.setId(disciplineDto.getId());
        discipline.setName(disciplineDto.getName());
        discipline.setResultType(disciplineDto.getResultType());
        return discipline;
    }
}
