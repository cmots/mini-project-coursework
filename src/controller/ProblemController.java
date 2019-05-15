/**
 * @author: cmots
 * @date: 2019/5/13 16:54
 * @description: controller of one problem, including creating and judging
 */
package controller;

import domain.Exam;
import domain.Problem;

import java.util.ArrayList;

public class ProblemController {

    /**
     * get the current problem and return it to view layer
     * @author: cmots
     * @param exam the basic data of this exam
     * @param txtController the controller of txt file, including the whole txt data
     * @return a complete problem
     */
    public Problem getProblem(Exam exam,TxtController txtController){
        Problem problem = new Problem();
        int currentLineNo = exam.getCurrentLineNo();

        //set id
        problem.setProblemId(exam.getCurrentProblemId());

        //set problem
        problem.setProblem(txtController.getStrings().get(exam.getCurrentLineNo()).substring(9));

        //set difficulty
        problem.setDifficulty(txtController.getStrings().get(exam.getCurrentLineNo()+1));

        String[] options = new String[4];
        ArrayList<String> answers = new ArrayList<>();

        //set answers and options
        for(int i = 2;i < 6;i++){
            String temp = txtController.getStrings().get(i + currentLineNo);
            String s;
            if(temp.endsWith("[Correct]")){
                s = temp.substring(0,temp.lastIndexOf(" [Correct]"));
                answers.add(s);
                options[i-2] = s;
            }
            else{
                options[i-2] = temp;
            }
        }
        problem.setAnswers(answers);
        problem.setOptions(options);

        return problem;
    }

    /**
     *
     * @author: cmots
     * @param exam the basic data of this exam
     * @param problem the current problem
     * @param answers answer(s) that user choose
     * @return 0:you are right
     * @return 1:you are wrong but still one chance
     * @return 2:wrong and have no chance
     */
    public int judge(Exam exam,Problem problem,ArrayList<String> answers){

        ArrayList<String> correctAnswers = problem.getAnswers();
        int score;

        if(problem.getDifficulty()=="Easy"){
            score = 1;
        }
        else if(problem.getDifficulty()=="Medium"){
            score = 2;
        }
        else {
            score = 3;
        }

        //a single choice question
        if(correctAnswers.size()==1){
            //answer is right
            if(correctAnswers.get(0) == answers.get(0)){
                ExamController.updateScore(exam,score);
                return 0;
            }
            else{
                return 2;
            }
        }

        //a multiple choice question
        else {
            if(correctAnswers.contains(answers.get(0))&&correctAnswers.contains(answers.get(1))){
                //all is correct
                ExamController.updateScore(exam,score * problem.getScoreTimes());
                return 0;
            }
            else{
                //answer is wrong
                if(problem.getScoreTimes()>1){
                    //first attempt
                    problem.setScoreTimes(1);
                    return 1;
                }
                else{
                    //second attempt
                    return 2;
                }
            }
        }
    }
}
