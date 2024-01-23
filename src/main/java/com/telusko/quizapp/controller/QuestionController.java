package com.telusko.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.quizapp.service.QuestionService;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telusko.quizapp.model.Question;

import java.util.*;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allquestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    
}
