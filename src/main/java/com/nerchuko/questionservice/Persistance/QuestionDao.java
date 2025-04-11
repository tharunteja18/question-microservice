package com.nerchuko.questionservice.Persistance;



import com.nerchuko.questionservice.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(value="select q.id from question q where q.category=:category order by rand() limit :noOfQuestions ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int noOfQuestions);


}
