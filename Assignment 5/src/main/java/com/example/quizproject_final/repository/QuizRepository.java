package com.example.quizproject_final.repository;

import com.example.quizproject_final.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

} // JpaRepository to work MySQL database with Spring Boot
