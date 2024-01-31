package com.telusko.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.dao.QuizDao;
import com.telusko.quizapp.model.Quiz;
import com.telusko.quizapp.model.Response;
import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.model.QuestionWrapper;

import java.util.*;

@Service
public class QuizService {
    
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
    
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        
        Quiz quiz = new Quiz();
        quiz.setTitle(title);

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> showQuizQuestion(Integer id) {
        
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for(Question question : questions){
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionWrappers.add(questionWrapper);
        }

        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);

    } 

    public ResponseEntity<String> submitQuiz(Integer id, List<Response> responses){
            
        int result = 0;
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int  i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                result ++;
            }
            i++;
        }

        return new ResponseEntity<>(String.valueOf(result), HttpStatus.OK);

    }

}
