/**
 * @author: cmots
 * @date: 2019/5/13 21:55
 * @description: the view layer class of problems
 */
package view;

import domain.Exam;
import domain.Problem;

import javax.swing.*;
import java.awt.*;

public class ProblemView {
    JFrame frame;
    private JTextArea textArea;
    private JPanel panel1;
    private JPanel panel2;
    private JButton[] buttons;
    private JButton restartBtn;
    private JTextArea scoreText;

    public ProblemView(Exam exam){
        //instantiate the basic components
        frame = new JFrame("Multiple Choice Questions");
        textArea = new JTextArea();
        buttons = new JButton[4];
        panel1 = new JPanel();
        panel2 = new JPanel();
        restartBtn = new JButton("reset score");
        frame.setSize(350,250);
        scoreText = new JTextArea();

        for(int i=0;i<4;i++)
        {
            buttons[i] = new JButton();
            buttons[i].setSize(250,50);
        }
        frame.getContentPane().add(panel1,BorderLayout.CENTER);
        frame.getContentPane().add(panel2,BorderLayout.SOUTH);

        //some extra settings
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1.setLayout(new GridLayout(4,1));
        panel2.setLayout(new FlowLayout());

        //button settings
        for(JButton button : buttons){
            panel1.add(button);
        }

        //set the position
        frame.add(textArea,BorderLayout.NORTH);
        panel2.add(scoreText);
        panel2.add(restartBtn);

        //textarea settings
        textArea.setFont(new Font("Serif",Font.BOLD,15));
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        scoreText.setEditable(false);
        scoreText.setBackground(Color.WHITE);

        frame.setVisible(true);
    }

    private void setText(Exam exam, Problem problem){
        textArea.setText(problem.getProblem());
        scoreText.setText("score = "+ exam.getScore());
        for(int i=0;i<4;i++){
            buttons[i].setText(problem.getOptions()[i]);
        }
        //single choice question
        if(problem.getAnswers().size()==1){
            panel1.setBorder(BorderFactory.createTitledBorder("Possible Answers: click one"));
        }
        else{
            panel1.setBorder(BorderFactory.createTitledBorder("Possible Answers: click two"));
        }
    }
}
