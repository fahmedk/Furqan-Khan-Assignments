package com.example.quizproject_final.controller;

import com.example.quizproject_final.model.Quiz;
import com.example.quizproject_final.service.QuizService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/quiz")
    public ResponseEntity<Quiz> createOrUpdateQuiz(@RequestBody Quiz quiz) {
        Quiz savedQuiz = quizService.createOrUpdateQuiz(quiz);
        return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
    }

    @DeleteMapping("/clear")
    @Transactional
    public ResponseEntity<String> clearQuizzes() {
        try {
            quizService.deleteAllQuizzes();
            return ResponseEntity.ok("All tasks have been deleted successfully.");
        } catch (Exception e) {
            // Log the exception as well
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting all tasks.");
        }
    }
}

/*
Albi's LoginController:
@RequestMapping("/user")
public class LoginController {
    private final LoginService loginService;
    public LoginController(LoginServiceImpl loginService) { this.loginService = loginService; }

    @PostMapping("/login")
    public Boolean login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return loginService.login(username, password);
    }

    @PostMapping("/logout")
    public boolean logout(@RequestBody User user) { return true; }
}
 */
