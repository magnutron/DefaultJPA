package org.example.defaultjpa;

import org.example.defaultjpa.entity.Discipline;
import org.example.defaultjpa.entity.Participant;
import org.example.defaultjpa.enums.Gender;
import org.example.defaultjpa.enums.SortingDirection;
import org.example.defaultjpa.repository.DisciplineRepository;
import org.example.defaultjpa.repository.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ParticipantRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    private Discipline discipline1;
    private Discipline discipline2;

    @BeforeEach
    public void setUp() {
        discipline1 = new Discipline();
        discipline1.setName("100m Sprint");
        discipline1.setResultType("Seconds");
        discipline1.setSortingDirection(SortingDirection.ASCENDING);
        discipline1 = entityManager.persistAndFlush(discipline1);

        discipline2 = new Discipline();
        discipline2.setName("Long Jump");
        discipline2.setResultType("Meters");
        discipline2.setSortingDirection(SortingDirection.DESCENDING);
        discipline2 = entityManager.persistAndFlush(discipline2);
    }

    @Test
    public void whenFindByGender_thenReturnParticipants() {
        // given
        Participant participant1 = new Participant();
        participant1.setName("John Doe Senior");
        participant1.setGender(Gender.Male);
        participant1.setDateOfBirth(LocalDate.parse("1990-01-01"));
        participant1.setClub("Club 1");
        participant1.setDisciplines(new HashSet<>(List.of(discipline1, discipline2)));
        entityManager.persistAndFlush(participant1);

        Participant participant2 = new Participant();
        participant2.setName("Jane Doe Senior");
        participant2.setGender(Gender.Female);
        participant2.setDateOfBirth(LocalDate.parse("1995-01-01"));
        participant2.setClub("Club 2");
        participant2.setDisciplines(new HashSet<>(List.of(discipline1, discipline2)));
        entityManager.persistAndFlush(participant2);

        // when
        List<Participant> found = participantRepository.findByGender(Gender.Female);

        // then
        assertThat(found).hasSize(1).extracting(Participant::getName).containsOnly("Jane Doe Senior");
    }

}