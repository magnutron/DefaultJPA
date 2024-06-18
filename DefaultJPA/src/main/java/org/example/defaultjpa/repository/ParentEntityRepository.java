package org.example.defaultjpa.repository;

import org.example.defaultjpa.entity.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentEntityRepository extends JpaRepository<ParentEntity, Integer> {
}
