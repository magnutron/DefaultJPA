package org.example.defaultjpa.repository;

import org.example.defaultjpa.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}

