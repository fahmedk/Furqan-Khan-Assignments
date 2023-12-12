package com.example.quizproject_final.service;

import com.example.quizproject_final.model.Questions;
import com.example.quizproject_final.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;

    @Autowired
    public QuestionsService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public List<Questions> getAllQuestions() {
        return questionsRepository.findAll();
    }

    public Optional<Questions> getQuestionById(Integer questionId) {
        return questionsRepository.findById(questionId);
    }

    public Questions createOrUpdateQuestion(Questions question) {
        return questionsRepository.save(question);
    }

    public void deleteQuestion(Integer questionId) {
        questionsRepository.deleteById(questionId);
    }

    public void deleteAllQuestions() {
        questionsRepository.deleteAll();
    }

}
