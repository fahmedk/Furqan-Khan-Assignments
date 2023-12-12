package com.example.quizproject_final.service;

import com.example.quizproject_final.model.Answers;
import com.example.quizproject_final.repository.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswersService {

    private final AnswersRepository answersRepository;

    @Autowired
    public AnswersService(AnswersRepository answersRepository) {
        this.answersRepository = answersRepository;
    }

    public List<Answers> getAllAnswers() {
        return answersRepository.findAll();
    }

    public Optional<Answers> getAnswerById(Integer answerId) {
        return answersRepository.findById(answerId);
    }

    public Answers createOrUpdateAnswer(Answers answer) {
        return answersRepository.save(answer);
    }

    public void deleteAnswer(Integer answerId) {
        answersRepository.deleteById(answerId);
    }

    public void deleteAllAnswers() {
        answersRepository.deleteAll();
    }

    // Additional methods can be implemented as needed
}
