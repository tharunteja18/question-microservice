package com.nerchuko.questionservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {

    private int id;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String questionTitle;

//    public QuestionWrapper(int id, String category, String option1, String option2, String option3, String option4) {
//        this.id = id;
//        this.category = category;
//        this.option1 = option1;
//        this.option2 = option2;
//        this.option3 = option3;
//        this.option4 = option4;
//
//    }
}
