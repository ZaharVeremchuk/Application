package com.cosmetology.application.controllers;

import com.cosmetology.application.dto.response.QuestionsDTO;
import com.cosmetology.application.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestionsDTO>> allQuestions(){
        return ResponseEntity.status(HttpStatus.OK).body(questionService.allQuestions());
    }
    @PostMapping("/save")
    public ResponseEntity<QuestionsDTO> saveQuestion(@Valid @RequestBody QuestionsDTO questionsDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.saveQuestion(questionsDTO));
    }

    @PostMapping("/{id}")
    public void deleteQuestion(@RequestParam("id") Long id){
        questionService.deleteQuestion(id);
    }

}
