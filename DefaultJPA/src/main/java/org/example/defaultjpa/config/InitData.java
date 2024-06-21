package org.example.defaultjpa.config;

import jakarta.transaction.Transactional;
import org.example.defaultjpa.entity.Discipline;
import org.example.defaultjpa.entity.Participant;
import org.example.defaultjpa.entity.Result;
import org.example.defaultjpa.enums.Gender;
import org.example.defaultjpa.enums.SortingDirection;
import org.example.defaultjpa.repository.DisciplineRepository;
import org.example.defaultjpa.repository.ParticipantRepository;
import org.example.defaultjpa.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class InitData {

    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;
    private final ResultRepository resultRepository;

    @Autowired // Optional if using constructor injection in Spring 4.3+
    public InitData(ParticipantRepository participantRepository, DisciplineRepository disciplineRepository, ResultRepository resultRepository) {
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
        this.resultRepository = resultRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        Discipline discipline1 = new Discipline();
        discipline1.setName("100m Sprint");
        discipline1.setResultType("Seconds");
        discipline1.setSortingDirection(SortingDirection.ASCENDING);
        disciplineRepository.save(discipline1);

        Discipline discipline2 = new Discipline();
        discipline2.setName("Long Jump");
        discipline2.setResultType("Meters");
        discipline2.setSortingDirection(SortingDirection.DESCENDING);
        disciplineRepository.save(discipline2);

        Participant participant1 = new Participant();
        participant1.setName("John Doe Senior");
        participant1.setGender(Gender.Male); // Assuming Gender is an enum with MALE as a constant
        participant1.setDateOfBirth(LocalDate.parse("1990-01-01"));
        participant1.setClub("Club 1");
        participant1.setDisciplines(new HashSet<Discipline>() {{
            add(discipline1);
            add(discipline2);
        }});
        participantRepository.save(participant1);

        Participant participant2 = new Participant();
        participant2.setName("Jane Doe Senior");
        participant2.setGender(Gender.Female);
        participant2.setDateOfBirth(LocalDate.parse("1995-01-01"));
        participant2.setClub("Club 2");
        participant2.setDisciplines(new HashSet<Discipline>() {{
            add(discipline1);
            add(discipline2);
        }});
        participantRepository.save(participant2);

        Participant participant3 = new Participant();
        participant3.setName("John Doe Junior");
        participant3.setGender(Gender.Male);
        participant3.setDateOfBirth(LocalDate.parse("2010-01-01"));
        participant3.setClub("Club 2");
        participant3.setDisciplines(new HashSet<Discipline>() {{
            add(discipline1);
            add(discipline2);
        }});
        participantRepository.save(participant3);

        Participant participant4 = new Participant();
        participant4.setName("Jane Doe Junior");
        participant4.setGender(Gender.Female);
        participant4.setDateOfBirth(LocalDate.parse("2015-01-01"));
        participant4.setClub("Club 1");
        participant4.setDisciplines(new HashSet<Discipline>() {{
            add(discipline1);
            add(discipline2);
        }});
        participantRepository.save(participant4);

        Result result1 = new Result();
        result1.setResultValue("10.5");
        result1.setDate(LocalDate.parse("2021-01-01"));
        result1.setParticipant(participant1);
        result1.setDiscipline(discipline1);
        resultRepository.save(result1);

        Result result2 = new Result();
        result2.setResultValue("5.0");
        result2.setDate(LocalDate.parse("2021-01-01"));
        result2.setParticipant(participant1);
        result2.setDiscipline(discipline2);
        resultRepository.save(result2);

        Result result3 = new Result();
        result3.setResultValue("11.0");
        result3.setDate(LocalDate.parse("2021-01-01"));
        result3.setParticipant(participant2);
        result3.setDiscipline(discipline1);
        resultRepository.save(result3);

        Result result4 = new Result();
        result4.setResultValue("5.5");
        result4.setDate(LocalDate.parse("2021-01-01"));
        result4.setParticipant(participant2);
        result4.setDiscipline(discipline2);
        resultRepository.save(result4);

        Result result5 = new Result();
        result5.setResultValue("11.5");
        result5.setDate(LocalDate.parse("2021-01-01"));
        result5.setParticipant(participant3);
        result5.setDiscipline(discipline1);
        resultRepository.save(result5);

        Result result6 = new Result();
        result6.setResultValue("4.5");
        result6.setDate(LocalDate.parse("2021-01-01"));
        result6.setParticipant(participant3);
        result6.setDiscipline(discipline2);
        resultRepository.save(result6);

        Result result7 = new Result();
        result7.setResultValue("12.0");
        result7.setDate(LocalDate.parse("2021-01-01"));
        result7.setParticipant(participant4);
        result7.setDiscipline(discipline1);
        resultRepository.save(result7);

        Result result8 = new Result();
        result8.setResultValue("4.0");
        result8.setDate(LocalDate.parse("2021-01-01"));
        result8.setParticipant(participant4);
        result8.setDiscipline(discipline2);
        resultRepository.save(result8);
    }
}
