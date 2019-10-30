import javax.swing.*;
import java.awt.*;

public class UserInterface {

    //A Boundary Class which deals only with the User Interface

    public UserInterface() {
        createScreen();
    }

    //Using Border Layout Manager
    private void createScreen() {
        JFrame f = new JFrame();

        JLabel title = new JLabel("Snakes And Ladders");

        JButton restartBtn = new JButton("Restart");

        JButton rollDiceBtn = new JButton("Roll Dice");

        JLabel noticeBoardLbl = new JLabel("Notice Board");

        //Center
        JScrollPane board = createBoard();

        f.add(title, BorderLayout.NORTH);
        f.add(restartBtn, BorderLayout.SOUTH);
        f.add(rollDiceBtn, BorderLayout.EAST);
        f.add(noticeBoardLbl, BorderLayout.WEST);
        f.add(board, BorderLayout.CENTER);

        f.setSize(600, 600);
        f.setVisible(true);
    }

    private JScrollPane createBoard() {
        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"ID","NAME","SALARY"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(jt);
        return sp;
    }
}
