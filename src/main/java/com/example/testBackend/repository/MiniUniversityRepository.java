package com.example.testBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.testBackend.entity.MiniUniversityEntity;

@Repository
public interface MiniUniversityRepository extends JpaRepository<MiniUniversityEntity, Integer> {

}
