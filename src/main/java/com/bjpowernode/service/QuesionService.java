package com.bjpowernode.service;

import com.bjpowernode.model.Question;

import java.util.List;

public interface QuesionService {

    Integer addQuestion(Question question);


    Question findQuestionByTitle(String title);

    List<Question> findQuestion();

    Question findQuestionById(Integer questionId);

    Integer updateQuestion(Question question);

    Integer deleteQuestion(Integer[] questionId);

    List<Question> findRandExams();
}
