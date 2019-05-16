/**
 * @author: cmots
 * @date: 2019/5/13 20:07
 * @description: the model of exam
 */
package domain;

public class Exam {
    private int currentLineNo = 0;
    private int currentProblemId = 1;
    private int score = 0;
    private String difficultySelected;
    private int maxNum = 0;
    private int maxLine = 0;

    public int getCurrentLineNo() {
        return currentLineNo;
    }

    public void setCurrentLineNo(int currentLineNo) {
        this.currentLineNo = currentLineNo;
    }

    public int getCurrentProblemId() {
        return currentProblemId;
    }

    public void setCurrentProblemId(int currentProblemId) {
        this.currentProblemId = currentProblemId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDifficultySelected() {
        return difficultySelected;
    }

    public void setDifficultySelected(String difficultySelected) {
        this.difficultySelected = difficultySelected;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMaxLine() {
        return maxLine;
    }

    public void setMaxLine(int maxLine) {
        this.maxLine = maxLine;
    }
}
