package com.example.quizproject_final.repository;

import com.example.quizproject_final.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
}