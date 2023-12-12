package com.example.quizproject_final.service;

import com.example.quizproject_final.model.Quiz;
import com.example.quizproject_final.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(int id) {
        return quizRepository.findById(id);
    }

    public Quiz createOrUpdateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(int id) {
        quizRepository.deleteById(id);
    }

    public void deleteAllQuizzes() {
        quizRepository.deleteAll();
    }
}

/*
Albi's LoginServiceImpl.java

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Boolean login(String username, String password) {
        if (username.equals("aarapi") && password.equals("12345"))
            return true;
        return false;
    }

    @Override
    public String logout(User user) {
        return "User "+user.getFullName() + " signed out successfully";
    }
}

 */