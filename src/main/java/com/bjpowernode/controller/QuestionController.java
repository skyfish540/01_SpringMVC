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
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        Question question=null;
        if ("".equals(title)||title==null){
            returnObject.setCode(CommonsConst.ERROR);
            returnObject.setErrorMessage("试题名不能为空*****");
            returnObject.setData(null);
            return returnObject;
        }else {
            question= quesionService.findQuestionByTitle(title);
        }
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
        Integer result=0;
        if ("".equals(question.getTitle())||question.getTitle()==null){
            model.addAttribute("errorMessage", "试题不能为空");
            return "question_Add";
        }else {
            result=quesionService.addQuestion(question);
        }

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

    @RequestMapping("/question/private/delete.do")
    public String deleteQuestion(Integer[] questionId,Model model){
        Integer result=quesionService.deleteQuestion(questionId);
        switch (result){
            case 0:
                model.addAttribute("errorMessage", "请选择要删除的试题");
                return "redirect:/question/private/find.do";
        }
        System.out.println("------------------------");
        System.out.println(result);
        model.addAttribute("success", "共删除"+result+"记录");

        return "redirect:/question/private/find.do";
    }

    @RequestMapping("/question/private/randExam.do")
    public String exams(Question question,Model model){
        List<Question> examList=quesionService.findRandExams();
        model.addAttribute("exams", examList);
        return "exam";
    }
    @RequestMapping(value = "/question/private/score.do",produces = "text/html;charset=utf-8")
    public @ResponseBody Object score(HttpServletRequest request){
        Map answerMap= new HashMap();
        List<String> myAnswerList=new ArrayList();
        int score=0;
        //获取请求参数的Map集合这个Map集合的key存放的是请求中的参数名，Map集合的value存放的是请求中的参数所对应的值
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key : parameterMap.keySet()){
            //判断请求中的参数名是否是一个answer开头如果是表示这个请求名所对应的数据是真正的正确答案 否则为我们选择的答案
            if (key.startsWith("answer")){
                //使用试题的编号以及答案作为key ,使用非null的数据作为value
                answerMap.put(key.substring(6)+parameterMap.get(key)[0],"");
            }else {//进入else表示这个请求名所对应的数据是我们在页面中选择的答题的答案
                //集合中存放试题的编号以及正确的答案
                myAnswerList.add(key+parameterMap.get(key)[0]);
            }
        }
        //计算分数
        for (String myAnswer :myAnswerList ){
            //判断指定的数据是否出现在Map集合的key中，如果出现则表示这道题用做对了，就加分。
            if (answerMap.containsKey(myAnswer)){
                score+=50;
            }
        }
        System.out.println(answerMap);
        System.out.println(myAnswerList);
        return "成绩："+score+"分";
    }


}
