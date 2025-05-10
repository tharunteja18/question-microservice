package com.nerchuko.questionservice.Service;



import com.nerchuko.questionservice.Model.Question;
import com.nerchuko.questionservice.Model.QuestionWrapper;
import com.nerchuko.questionservice.Model.Response;
import com.nerchuko.questionservice.Persistance.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    @Autowired
    private QuestionDao questionDao;


    //method-1
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    //method-2
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category)
    {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public String addQuestion(Question question)
    {
        questionDao.save(question);
        return "Question added";
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer noOfQuestions) {

       List<Integer> questionIds =  questionDao.findRandomQuestionsByCategory(category, noOfQuestions);
        return new ResponseEntity<>(questionIds,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {

        List<QuestionWrapper> questionWrapper= new ArrayList<>();
        List<Question> question =new ArrayList<>();

        for(Integer id:questionIds)
        {
            question.add(questionDao.findById(id).get());
        }
        for(Question q:question)
        {
            QuestionWrapper qw=new QuestionWrapper();
            qw.setId(q.getId());
            qw.setQuestionTitle(q.getQuestionTitle());
            qw.setCategory(q.getCategory());
            qw.setOption1(q.getOption1());
            qw.setOption2(q.getOption2());
            qw.setOption3(q.getOption3());
            qw.setOption4(q.getOption4());
            questionWrapper.add(qw);
        }

        return new ResponseEntity<>(questionWrapper,HttpStatus.OK);

    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score=0;

        for(Response r:responses)
        {
            Question q =questionDao.findById(r.getId()).get();
            if(r.getResponse().equals(q.getRightAnswer()))
            {
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
