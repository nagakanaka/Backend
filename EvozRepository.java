package com.tritern.evozspecial.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tritern.evozspecial.entity.EvozEntity;

@Repository
public interface EvozRepository extends JpaRepository<EvozEntity, Long> {

    boolean existsByEmailid(String emailid);

    @Query(value = "SELECT * FROM evoztable WHERE emailid = ?1", nativeQuery = true)
    Optional<EvozEntity> findByEmailid(String emailid);

    @Query(value = "SELECT * FROM evoztable WHERE name = ?1", nativeQuery = true)
    EvozEntity findByName(String name);

    @Query(value = "SELECT * FROM evoztable WHERE name LIKE CONCAT('%', ?1, '%') OR username LIKE CONCAT('%', ?1, '%') OR emailid LIKE CONCAT('%', ?1, '%') LIMIT 9 OFFSET 0", nativeQuery = true)
    List<EvozEntity> searchUsers(String keyword);

    @Query(value = "SELECT COUNT(*) FROM evoztable WHERE name LIKE CONCAT('%', ?1, '%') OR username LIKE CONCAT('%', ?1, '%') OR emailid LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    Integer searchLength(String keyword);
}
