/**
 * @author: cmots
 * @date: 2019/5/13 19:46
 * @description: a model of each problem
 */
package domain;

import java.util.ArrayList;

public class Problem {
    private int problemId;
    private String problem;
    private ArrayList<String> answers;
    private String[] options;
    private String difficulty;
    private int scoreTimes = 2;

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getScoreTimes() {
        return scoreTimes;
    }

    public void setScoreTimes(int scoreTimes) {
        this.scoreTimes = scoreTimes;
    }
}
