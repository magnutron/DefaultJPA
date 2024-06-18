package org.example.defaultjpa.repository;

import jakarta.transaction.Transactional;
import org.example.defaultjpa.entity.Entity2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Entity2Repository extends JpaRepository<Entity2, Integer>{
    Optional<Entity2> findById(Integer id);


    @Transactional
    @Modifying
    @Query("DELETE FROM Entity2 e WHERE e.entity1.id = :entity1Id")
    void deleteByEntity1Id(Integer entity1Id);
}
