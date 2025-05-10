package com.nerchuko.questionservice.Service;


import com.nerchuko.questionservice.Model.Question;
import com.nerchuko.questionservice.Model.QuestionWrapper;
import com.nerchuko.questionservice.Model.Response;
import com.nerchuko.questionservice.Persistance.QuestionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionService questionService;


        List<Question> questions;
        List<Question> emptyQuestions;
        int noOfQuestions;

        List<Integer> questionIds;

        List<QuestionWrapper> questionWrapper;
        List<Question> question;

        String category;
        @BeforeEach
        void setUp()
        {
             questions=List.of(new Question(1,"java","easy","class","interface","implements","Extend","Which keyword in java is used to create a subclass","Extends"),
                    new Question(2,"java","easy","int","char","float","Integer","which one is not related to primitive","Integer"));

             emptyQuestions= Arrays.asList();

             category="java";
             noOfQuestions=2;
             questionIds= Arrays.asList(1,2);
//             questionWrapper=Arrays.asList();
        }
        @Test
        public void getAllQuestionsTest()
        {
            when(questionDao.findAll()).thenReturn(questions);
            ResponseEntity<List<Question>> response=questionService.getAllQuestions();

            assertEquals("status code", HttpStatus.OK, response.getStatusCode());
            assertEquals("size comparing",2,response.getBody().size());

        }
        @Test
        public void getAllQuestionsTestWithException()
        {
            when(questionDao.findAll()).thenThrow(RuntimeException.class);
            ResponseEntity<List<Question>> response=questionService.getAllQuestions();

            assertEquals("respone_check",Arrays.asList(),response.getBody());
            assertEquals("Exception",HttpStatus.BAD_REQUEST,response.getStatusCode());
        }


    @Test
    public void getQuestionsByCategoryTest()
    {
        when(questionDao.findByCategory(category)).thenReturn(questions);
        ResponseEntity<List<Question>> response = questionService.getQuestionsByCategory(category);
        assertEquals("status code", HttpStatus.OK,response.getStatusCode());
        assertEquals("checking the size",2,response.getBody().size());
    }

    @Test
    public void getQuestionByCategoryTestWithException() {
        when(questionDao.findByCategory(category)).thenThrow(RuntimeException.class);
        ResponseEntity<List<Question>> response = questionService.getQuestionsByCategory(category);
        assertEquals("checking the status",HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("checking the body",new ArrayList<>(),response.getBody());

    }

    @Test
    public void addQuestionTest()
    {
        when(questionDao.save(questions.get(0))).thenReturn(questions.get(0));
        String result=questionService.addQuestion(questions.get(0));
        assertEquals("return type check","Question added", result);
    }

    @Test
    public void getQuestionsForQuizTest()
    {
        when(questionDao.findRandomQuestionsByCategory(category,noOfQuestions)).thenReturn(questionIds);
        ResponseEntity<List<Integer>> response=questionService.getQuestionsForQuiz(category,noOfQuestions);

        assertEquals("status checking", OK, response.getStatusCode());
        assertEquals("size",2, response.getBody().size());
    }


    @Test
    public void getQuestionsFromIdTest()
    {
        when(questionDao.findById(1)).thenReturn(Optional.of(questions.get(0)));
        when(questionDao.findById(2)).thenReturn(Optional.of(questions.get(1)));
        ResponseEntity<List<QuestionWrapper>> response=questionService.getQuestionsFromId(questionIds);
    }


//    public ResponseEntity<Integer> getScore(List<Response> responses) {
//        int score=0;
//
//        for(Response r:responses)
//        {
//            Question q =questionDao.findById(r.getId()).get();
//            if(r.getResponse().equals(q.getRightAnswer()))
//            {
//                score++;
//            }
//        }
//        return new ResponseEntity<>(score,HttpStatus.OK);
//    }

    @Test
    public void getScoreTest()
    {
        when(questionDao.findById(1)).thenReturn(Optional.of(questions.get(0)));
        when(questionDao.findById(2)).thenReturn(Optional.of(questions.get(1)));
        ResponseEntity<Integer> response=questionService.getScore(Arrays.asList(new Response(1,"Extend"),new Response(2,"Integer")));

        assertEquals("status", OK,response.getStatusCode());
    }



}
