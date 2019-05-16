/**
 * @author: cmots
 * @date: 2019/5/15 21:00
 * @description: the view layer of choosing difficulty
 */
package view;

import controller.ExamController;
import domain.Exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyView {
    JFrame frame;
    private JTextArea textArea;
    private JRadioButton[] radioButtons;
    private JButton okBtn;
    int select = 0;
    public Exam exam = new Exam();

    public DifficultyView(){
        //instantiate the basic components
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Multiple Choice Questions");
        textArea = new JTextArea();
        radioButtons = new JRadioButton[3];
        okBtn = new JButton("Let's start!");
        frame.setSize(300,200);
        frame.setLocation(220,160);
        radioButtons[0] = new JRadioButton("Easy",false);
        radioButtons[1] = new JRadioButton("Medium",true);
        radioButtons[2] = new JRadioButton("Hard",false);

        //some extra settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //single choice radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        for(int i=0;i<3;i++){
            radioButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
            buttonGroup.add(radioButtons[i]);
        }
        buttonGroup.add(radioButtons[0]);
        buttonGroup.add(radioButtons[1]);
        buttonGroup.add(radioButtons[2]);

        //set the position
        frame.getContentPane().add(textArea, BorderLayout.NORTH);
        frame.getContentPane().add(radioButtons[0],BorderLayout.WEST);
        frame.getContentPane().add(radioButtons[1],BorderLayout.CENTER);
        frame.getContentPane().add(radioButtons[2],BorderLayout.EAST);
        frame.getContentPane().add(okBtn,BorderLayout.SOUTH);

        //set textarea
        textArea.setText("You have several multiple choice questions.\n" +
                "Choose a difficulty and start now!");
        textArea.setFont(new Font("Serif",Font.BOLD,15));
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);

        //listener of button
        okBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(select = 0;select < 3;select++){
                            if(radioButtons[select].isSelected())
                            {
                                exam.setDifficultySelected(radioButtons[select].getText());
                                break;
                            }
                        }
                        //dispose this frame and open next one
                        frame.dispose();
                        ProblemView problemView = new ProblemView(exam);
                    }
                }
        );
        frame.setVisible(true);
    }
}
