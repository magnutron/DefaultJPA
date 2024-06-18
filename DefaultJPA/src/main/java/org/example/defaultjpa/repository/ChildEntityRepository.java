package org.example.defaultjpa.repository;

import org.example.defaultjpa.entity.ChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildEntityRepository extends JpaRepository<ChildEntity, Integer> {
}
