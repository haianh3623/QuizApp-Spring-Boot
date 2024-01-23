package com.telusko.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.quizapp.model.Question;


@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    
    

}
