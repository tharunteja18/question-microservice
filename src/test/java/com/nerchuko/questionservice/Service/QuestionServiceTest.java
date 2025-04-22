package com.nerchuko.questionservice.Service;

import com.nerchuko.questionservice.Model.Question;
import com.nerchuko.questionservice.Persistance.QuestionDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Slf4j
public class QuestionServiceTest {

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionService questionService;



    private List<Question> questions;

    private List<Question> emptyQuestions;

    private Question singleQuesion=new Question(1,"Java","Easy","class","interface","implements","Extend","Which keyword in java is used to create a subclass","Extends");
    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);

        questions= Arrays.asList(new Question(1,"Java","Easy","class","interface","implements","Extend","Which keyword in java is used to create a subclass","Extends"),
                new Question(2,"Java","Medium","option1","option2","option3","option4","In java, what is the default value for uninitialized boolean value?","false"));

        emptyQuestions=Arrays.asList();
    }

    @Test
    public void getAllQuestionsTest() {

//        log.info("Inside the GetAllQuestionsTest Method");
        // mocking the behavior of the questionDao
        when(questionDao.findAll()).thenReturn(questions);
        // calling the method to test
        ResponseEntity<List<Question>> response=questionService.getAllQuestions();

        // validating the result or response
        // first argument is expected, second is actual in  assertEquals
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("HttpStatus.OK  :"+HttpStatus.OK);
        System.out.println("response.getStatusCode()   :"+response.getStatusCode());

        assertEquals(questions,response.getBody());
        System.out.println("questions  :"+questions);
        System.out.println("response.getBody()   :"+response.getBody());


    }


    @Test
    public void testGetAllQuestionsWhenExceptionOccurs()
    {
        when(questionDao.findAll()).thenThrow(new RuntimeException("Test Exception"));

        ResponseEntity<List<Question>> response=questionService.getAllQuestions();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        System.out.println("HttpStatus.BAD_REQUEST  :"+HttpStatus.BAD_REQUEST);
        System.out.println("response.getStatusCode()   :"+response.getStatusCode());
        assertEquals(new ArrayList<>(), response.getBody());
        System.out.println("new ArrayList<>()   :"+new ArrayList<>());
        System.out.println("response.getBody()   :"+response.getBody());
    }

    @Test
    public void testGetAllQuestionsWhenEmptyList()
    {
        when(questionDao.findAll()).thenThrow(new RuntimeException("Empty list..."));

        ResponseEntity<List<Question>> response = questionService.getAllQuestions();

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        System.out.println("HttpStatus.BAD_REQUEST.   :"+HttpStatus.BAD_REQUEST);
        System.out.println("response.getStatusCode()  :"+response.getStatusCode());

        assertEquals(new ArrayList<>(), response.getBody());
        System.out.println("new ArrayList<>()   :"+new ArrayList<>());
        System.out.println("response.getBody()   :"+response.getBody());

    }



        @Test
        public void testAddQuestion()
        {
        String result =questionService.addQuestion(singleQuesion);
        assertEquals("Question added", result);
        }


//    public ResponseEntity<List<Question>> getQuestionsByCategory(String category)
//    {
//        try {
//            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
//    }
    @Test
    public void testGetQuestionsByCategory()
    {
        String category="java";
        when(questionDao.findByCategory(category)).thenReturn(questions);

        ResponseEntity<List<Question>> questionsByCategory = questionService.getQuestionsByCategory(category);

        assertEquals(HttpStatus.OK,questionsByCategory.getStatusCode());
        assertEquals(questions,questionsByCategory.getBody());
    }
//    @Test
//    public void testGetQuestionsByCategoryException()
//    {
//        String category="python";
//        when(questionDao.findByCategory(category)).thenThrow(new RuntimeException("questions for category is not available"));
//
//        ResponseEntity<List<Question>> questionsByCategory = questionService.getQuestionsByCategory(category);
//
//        assertEquals(HttpStatus.BAD_REQUEST,questionsByCategory.getStatusCode());
//
//
//
//    }

}
