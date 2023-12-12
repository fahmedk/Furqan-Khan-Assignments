package com.example.quizproject_final.controller;

import com.example.quizproject_final.model.Answers;
import com.example.quizproject_final.service.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswersController {

    private final AnswersService answersService;

    @Autowired
    public AnswersController(AnswersService answersService) {
        this.answersService = answersService;
    }

    @PostMapping("/answer")
    public ResponseEntity<Answers> createOrUpdateAnswer(@RequestBody Answers answer) {
        Answers savedAnswer = answersService.createOrUpdateAnswer(answer);
        return new ResponseEntity<>(savedAnswer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answers> getAnswerById(@PathVariable Integer id) {
        return answersService.getAnswerById(id)
                .map(answer -> ResponseEntity.ok(answer))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Integer id) {
        answersService.deleteAnswer(id);
        return ResponseEntity.ok("Answer deleted successfully");
    }
}

