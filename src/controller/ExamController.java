/**
 * @author: cmots
 * @date: 2019/5/13 20:05
 * @description: the controller of the whole exam
 */
package controller;

import domain.Exam;

public class ExamController {

    /**
     * update user's score
     * @author: cmots
     * @param score the score that user get this problem
     * @return
     */
    public static void updateScore(Exam exam,int score){
        exam.setScore(exam.getScore() + score);
    }

    /**
     * reset user's score and problem
     * @author: cmots
     * @param
     * @return
     */
    public static void reset(Exam exam){
        exam.setScore(0);
        exam.setCurrentLineNo(0);
        exam.setCurrentProblemId(1);
    }

    /**
     * update the mark of problems
     * @author: cmots
     * @param maxProblem the number of all the problems, get with TxtController
     * @return true - update is completed. false - no more problems
     */
    public static boolean nextProblem(Exam exam,int maxProblem){
        if(exam.getCurrentProblemId()+1<=maxProblem)
        {
            exam.setCurrentProblemId(exam.getCurrentProblemId() + 1);
            exam.setCurrentLineNo(exam.getCurrentLineNo() + 8);
            return true;
        }
        else{
            return false;
        }
    }

}
