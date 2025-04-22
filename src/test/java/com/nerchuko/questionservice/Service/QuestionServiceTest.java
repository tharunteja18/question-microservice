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
    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);

        questions= Arrays.asList(new Question(1,"Java","Easy","class","interface","implements","Extend","Which keyword in java is used to create a subclass","Extends"),
                new Question(2,"Java","Medium","option1","option2","option3","option4","In java, what is the default value for uninitialized boolean value?","false"));
    }

    @Test
    public void getAllQuestionsTest() {

        log.info("Inside the GetAllQuestionsTest Method");
        // mocking the behavior of the questionDao
        when(questionDao.findAll()).thenReturn(questions);
        // calling the method to test
        ResponseEntity<List<Question>> response=questionService.getAllQuestions();

        // validating the result or response
        // first argument is expected, second is actual in  assertEquals
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questions,response.getBody());

        log.info("End of GetAllQuestionsTest Method");

    }

    //    public ResponseEntity<List<Question>> getAllQuestions()
//    {
//        log.info("Inside the QuestionService.getAllQuestions Method");
//        try {
//            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
//    }

    @Test
    public void testGetAllQuestionsWhenExceptionOccurs()
    {
        when(questionDao.findAll()).thenThrow(new RuntimeException("Test Exception"));

        ResponseEntity<List<Question>> response=questionService.getAllQuestions();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new ArrayList<>(), response.getBody());
    }


}
