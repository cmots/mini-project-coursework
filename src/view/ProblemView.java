/**
 * @author: cmots
 * @date: 2019/5/13 21:55
 * @description: the view layer class of problems
 */
package view;

import controller.ExamController;
import controller.ProblemController;
import controller.TxtController;
import domain.Exam;
import domain.Problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProblemView {
    JFrame frame;
    private JTextArea textArea;
    private JPanel panel1;
    private JPanel panel2;
    private JButton[] buttons;
    private JButton restartBtn;
    private JTextArea scoreText;
    TxtController txtController;
    ArrayList<String> userAnswer = new ArrayList<>();
    Exam exam;
    private JTextArea message;
    Problem problem = new Problem();

    public ProblemView(Exam exam){
        try{
            txtController = new TxtController();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        ExamController.setMaxNum(exam,txtController);
        this.exam=exam;
        //instantiate the basic components
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Multiple Choice Questions");
        textArea = new JTextArea();
        buttons = new JButton[4];
        panel1 = new JPanel();
        panel2 = new JPanel();
        restartBtn = new JButton("reset score");
        frame.setSize(380,260);
        frame.setLocation(220,160);
        scoreText = new JTextArea();
        message = new JTextArea();

        for(int i=0;i<4;i++)
        {
            buttons[i] = new JButton();
            buttons[i].setSize(250,50);
        }
        frame.getContentPane().add(panel1,BorderLayout.CENTER);
        frame.getContentPane().add(panel2,BorderLayout.SOUTH);

        //some extra settings

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1.setLayout(new GridLayout(4,1));
        panel2.setLayout(new FlowLayout());

        //button settings
        for(JButton button : buttons){
            panel1.add(button);
        }

        //set the position
        frame.add(textArea,BorderLayout.NORTH);
        panel2.add(message,BorderLayout.WEST);
        panel2.add(scoreText);
        panel2.add(restartBtn);

        //textarea settings
        textArea.setFont(new Font("Serif",Font.BOLD,15));
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        scoreText.setEditable(false);
        scoreText.setBackground(Color.WHITE);

        if(txtController.getEmptyTag()==1){
            panel1.removeAll();
            textArea.setText("error!txt file is empty!");
            panel1.repaint();
        }

        setText(ProblemController.getProblem(exam,txtController));
        //listener
        restartBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ExamController.reset(exam);
                        frame.dispose();
                        DifficultyView difficultyView = new DifficultyView();
                    }
                }
        );
        for(JButton button : buttons){
            button.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(button.getForeground()==Color.BLACK) {
                                userAnswer.add(button.getText());
                                button.setForeground(Color.RED);
                            }
                            else{
                                userAnswer.remove(0);
                                button.setForeground(Color.BLACK);
                            }
                            buttonAction();
                        }
                    }
            );
        }
        frame.setVisible(true);
    }

    /**
     * set all text to frame
     * @author: cmots
     * @param problem this problem
     * @return
     */
    private void setText(Problem problem){
        if(problem == null){
            panel1.removeAll();
            textArea.setText("error!no question is the difficulty you chose!");
            panel1.repaint();
            return;
        }
        textArea.setText("Question " + exam.getCurrentProblemId() + " of " + exam.getMaxNum() + ": " + problem.getProblem());
        scoreText.setText("score = "+ exam.getScore());
        for(int i=0;i<4;i++){
            buttons[i].setText(problem.getOptions()[i]);
            buttons[i].setForeground(Color.BLACK);
            buttons[i].repaint();
        }
        //single choice question
        if(problem.getAnswers().size()==1){
            panel1.setBorder(BorderFactory.createTitledBorder("Possible Answers: click one"));
        }
        else{
            panel1.setBorder(BorderFactory.createTitledBorder("Possible Answers: click two"));
        }

    }

    /**
     * actions when click option buttons
     * @author: cmots
     */
    private void buttonAction() {
        int result;

        if(problem.getScoreTimes()==2){
            problem = ProblemController.getProblem(exam,txtController);
        }

        if (userAnswer.size() == problem.getAnswers().size()) {
            result = ProblemController.judge(exam, problem, userAnswer);
        } else {
            return;
        }

        if (result == 0) {
            userAnswer.clear();
            message.setText("last question :right    ");
            if (ExamController.nextProblem(exam)){
                setText(ProblemController.getProblem(exam,txtController));
            } else {
                scoreText.setText("score = "+ exam.getScore());
                noMoreQuestions();
            }
        } else if (result == 1) {
            userAnswer.clear();
            message.setText("you are wrong but\nhave another chance");
            setText(ProblemController.getProblem(exam,txtController));
        } else {
            userAnswer.clear();
            message.setText("last question: wrong    ");
            if (ExamController.nextProblem(exam)) {
                setText(ProblemController.getProblem(exam,txtController));
            } else {
                scoreText.setText("score = "+ exam.getScore());
                noMoreQuestions();
            }
        }
    }
    /**
     * when here are no more question, what will happen
     * @author: cmots
     * @param
     * @return
     */
    private void noMoreQuestions(){
        panel1.removeAll();
        panel1.setBorder(BorderFactory.createTitledBorder("want to continue?"));
        JTextArea area = new JTextArea("Restart or quit!\n" +
                "restart can choose another difficulty and see more questions!");
        area.setEditable(false);
        panel1.add(area,BorderLayout.CENTER);
        textArea.setText("No more questions!");
        panel1.repaint();
    }
}
