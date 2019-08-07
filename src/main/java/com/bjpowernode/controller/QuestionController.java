package com.bjpowernode.controller;

import com.bjpowernode.commons.CommonsConst;
import com.bjpowernode.commons.CommonsReturnObject;
import com.bjpowernode.model.Question;
import com.bjpowernode.service.QuesionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Controller
public class QuestionController {
    @Resource
    private QuesionService quesionService;

    @RequestMapping("/question/check.do")
    public @ResponseBody Object questionCheck(String title){
        CommonsReturnObject returnObject = new CommonsReturnObject();
       Question question= quesionService.findQuestionByTitle(title);
       if (question!=null){
           returnObject.setCode(CommonsConst.ERROR);
           returnObject.setErrorMessage("试题已存在,请修改!!!");
           returnObject.setData(null);
           return returnObject;
       }
        returnObject.setCode(CommonsConst.OK);
        returnObject.setErrorMessage("试题可用");
        returnObject.setData(null);
        return returnObject;
    }

    @RequestMapping("/question/private/add.do")
    public String addQuestion(Question question, Model model ){
        Integer result=quesionService.addQuestion(question);
        switch (result){
            case 1:
                model.addAttribute("errorMessage", "试题已存在,请修改***");
                model.addAttribute("questionData", question);
                return "question_Add";
        }

        return "redirect:/question/private/find.do";
    }

    @RequestMapping("/question/private/find.do")
    public String findQuestion(Model model){
        List<Question> questionList=quesionService.findQuestion();
        model.addAttribute("questionList", questionList);
        return "question_show";
    }

    @RequestMapping("/question/private/toUpdate.do")
    public String toUpdataQuestion(Integer questionId,Model model){
        Question question=quesionService.findQuestionById(questionId);
        model.addAttribute("question", question);
        return "question_update";
    }

    @RequestMapping("/question/private/update.do")
    public String updateQuestion(Question question){
        Integer result=quesionService.updateQuestion(question);

        return "redirect:/question/private/find.do";
    }



}
