package com.example.quizproject_final.controller;

import com.example.quizproject_final.model.Questions;
import com.example.quizproject_final.service.QuestionsService;
import com.example.quizproject_final.service.QuizService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsService questionsService;
    private QuizService quizService;

    @Autowired
    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        List<Questions> questions = questionsService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questions> getQuestionById(@PathVariable Integer id) {
        return questionsService.getQuestionById(id)
                .map(question -> ResponseEntity.ok(question))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/question")
    public ResponseEntity<Questions> createOrUpdateQuestion(@RequestBody Questions question) {
        Questions savedQuestion = questionsService.createOrUpdateQuestion(question);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        try {
            questionsService.deleteQuestion(id);
            return ResponseEntity.ok("Question has been successfully deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.. please try again.");
        }
    }

    @DeleteMapping("/clear")
    @Transactional
    public ResponseEntity<String> clearQuestions() {
        try {
            questionsService.deleteAllQuestions();
            return ResponseEntity.ok("All questions have been successfully deleted.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.. please try again.");
        }
    }
}
