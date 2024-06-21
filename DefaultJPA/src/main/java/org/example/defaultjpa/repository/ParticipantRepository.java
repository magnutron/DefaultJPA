package org.example.defaultjpa.repository;

import org.example.defaultjpa.entity.Participant;
import org.example.defaultjpa.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByGender(Gender gender);
}

