package com.nerchuko.questionservice.Controller;



import com.nerchuko.questionservice.Model.Question;
import com.nerchuko.questionservice.Model.QuestionWrapper;
import com.nerchuko.questionservice.Model.Response;
import com.nerchuko.questionservice.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cate}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("cate") String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("addQuestion")
    public String addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    //generate or create
    // returning the question ids
    @GetMapping("generate")
    public ResponseEntity<List<Integer>>getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer noOfQuestions)
    {
        return questionService.getQuestionsForQuiz(category, noOfQuestions);

    }

    //getQuestions--> postMapping from quizService we get list of questionIds
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds)
    {
        return questionService.getQuestionsFromId(questionIds);
    }

    //score
    @PostMapping("score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }



}
