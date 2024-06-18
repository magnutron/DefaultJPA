package org.example.defaultjpa.repository;

import org.example.defaultjpa.entity.Entity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Entity1Repository extends JpaRepository<Entity1, Integer>{
    Optional<Entity1> findById(Integer id);
}
