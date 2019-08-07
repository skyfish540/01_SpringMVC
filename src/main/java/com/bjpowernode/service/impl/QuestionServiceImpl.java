package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.QuestionMapper;
import com.bjpowernode.model.Question;
import com.bjpowernode.service.QuesionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service("quesionService")
public class QuestionServiceImpl implements QuesionService {
    @Resource
    private QuestionMapper questionMapper;

    @Override
    public Integer addQuestion(Question question) {
        Question tempQuestion = questionMapper.selectQuestionByTitle(question.getTitle());
        if (tempQuestion!=null){
            return 1;
        }
        questionMapper.insert(question);
        return 0;
    }

    @Override
    public Question findQuestionByTitle(String title)
    {
        return questionMapper.selectQuestionByTitle(title);
    }

    @Override
    public List<Question> findQuestion() {
        List<Question> questions=questionMapper.selectQuestion();
        return questions;
    }

    @Override
    public Question findQuestionById(Integer questionId) {
        Question question = questionMapper.selectByPrimaryKey(questionId);
        return question;
    }

    @Override
    public Integer updateQuestion(Question question) {
        int result = questionMapper.updateByPrimaryKey(question);
        return result;
    }

    @Override
    public Integer deleteQuestion(Integer[] questionId) {
        Integer result=0;
        if (questionId!=null){
            for (Integer id : questionId){
                result = questionMapper.deleteByPrimaryKey(id);
                result+=1;
            }
            return (result-1);
        }else {
            return 0;
        }
    }
}
