/**
 * @author: cmots
 * @date: 2019/5/13 16:54
 * @description: controller of one problem
 */
package controller;

import domain.Problem;

import java.util.ArrayList;

public class ProblemController {

    /**
     * get the current problem and return it to view layer
     * @author: cmots
     * @param examController the controller of the whole exam
     * @param txtController the controller of txt file, including the whole txt data
     * @return a complete problem
     */
    public Problem getProblem(ExamController examController,TxtController txtController){
        Problem problem = new Problem();
        int currentLineNo = examController.getExam().getCurrentLineNo();

        //set id
        problem.setProblemId(examController.getExam().getCurrentProblemId());
        //set problem
        problem.setProblem(txtController.getStrings().get(examController.getExam().getCurrentLineNo()).substring(9));

        String[] options = new String[4];
        ArrayList<String> answers = new ArrayList<>();

        //set answers and options
        for(int i = 1;i < 5;i++){
            String temp = txtController.getStrings().get(i + currentLineNo);
            String s;
            if(temp.endsWith("[Correct]")){
                s = temp.substring(0,temp.lastIndexOf(" [Correct]"));
                answers.add(s);
                options[i-1] = s;
            }
            else{
                options[i-1] = temp;
            }
        }
        problem.setAnswers(answers);
        problem.setOptions(options);

        return problem;
    }
}
