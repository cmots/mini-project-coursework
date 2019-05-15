/**
 * @author: cmots
 * @date: 2019/5/15 21:00
 * @description: the view layer of choosing difficulty
 */
package view;

import domain.Exam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyView {
    JFrame frame;
    private JTextArea textArea;
    private JRadioButton[] radioButtons;
    private JButton okBtn;
    int select = 0;
    public Exam exam;

    public DifficultyView(){
        frame = new JFrame("Multiple Choice Questions");
        textArea = new JTextArea();
        radioButtons = new JRadioButton[3];
        okBtn = new JButton("Add");
        frame.setSize(100,200);
        radioButtons[0] = new JRadioButton("Easy",false);
        radioButtons[1] = new JRadioButton("Medium",true);
        radioButtons[2] = new JRadioButton("Hard",false);
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea.setText("You have several multiple choice questions.\n" +
                "Choose a difficulty and start now!");

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
                        ProblemView problemView = new ProblemView(exam);
                    }
                }
        );
        frame.setVisible(true);
    }

}
