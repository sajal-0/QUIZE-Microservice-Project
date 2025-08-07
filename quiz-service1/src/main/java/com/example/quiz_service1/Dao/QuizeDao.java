package com.example.quiz_service1.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_service1.Entity.Quize;

@Repository
public interface QuizeDao extends JpaRepository<Quize, Integer>{

}
