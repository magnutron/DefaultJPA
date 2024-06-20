package org.example.defaultjpa.repository;

import org.example.defaultjpa.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}

