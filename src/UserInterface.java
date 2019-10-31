import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A Boundary Class which deals only with the User Interface
public class UserInterface {

    public static int diceRoll = 0;
    public static boolean diceClick = false, restartClick = false;

    private JFrame frame = new JFrame();

    private JLabel noticeBoardLbl = new JLabel("Notice Board");
    private JLabel title = new JLabel("Snakes And Ladders");
    public JButton restartBtn = new JButton("Restart");
    private JButton rollDiceBtn = new JButton("Roll Dice");

    public UserInterface() {
        createScreen();
    }

    //Using Border Layout Manager
    private void createScreen() {

        //Notice Board
        noticeBoardLbl.setText("Notifications will be displayed here");

        //Center
        JPanel board = createBoard();

        //Roll Dice Btn
        rollDiceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceRoll = Dice.rollDice();
                diceClick = true;
            }
        });

        //Restart Button
        restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frame.add(title, BorderLayout.NORTH);
        frame.add(noticeBoardLbl, BorderLayout.SOUTH);
        frame.add(rollDiceBtn, BorderLayout.EAST);
        frame.add(restartBtn, BorderLayout.WEST);
        frame.add(board, BorderLayout.CENTER);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    int row = 5, col = 6;
    int matrix[][] = new int[row][col];

    private JPanel createBoard() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(row, col));

        int i = 0;
        for (int r = 0; r < row; r++) {
            if (r == 4) {
                i = 1;
            } else if (r == 3) {
                i = 12;
            } else if (r == 2) {
                i = 13;
            } else if (r == 1) {
                i = 24;
            } else if (r == 0) {
                i = 25;
            }
            for (int c = 0; c < col; c++) {
                Tile tile = new Tile(r, c, matrix, i);
                tile.setText(String.valueOf(i));
                tile.putClientProperty("id", i);
                if (r % 2 == 0) {
                    i++;
                } else {
                    i--;
                }
                p.add(tile);
            }
        }
        return p;
    }

    public void setNoticeBoardTxt(String text) {
        noticeBoardLbl.setText(text);
    }

    public String getNoticeBoardTxt() {
        return noticeBoardLbl.getText();
    }

    public static class Tile extends JButton {

        private final int[][] fModel;
        private final int fX;
        private final int fY;

        private final int no;

        public Tile(final int x, final int y, final int[][] model, final int no) {
            fX = x;
            fY = y;
            fModel = model;

            this.no = no;

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object property = getClientProperty("id");
                    if (property instanceof Integer) {
                        int objectCounter = ((Integer) property);
                        // do stuff
                        System.out.println("Property: " + objectCounter);
                    }
                }
            });
        }

//        private void updateNameFromModel() {
//            setText(String.valueOf(fModel[fX][fY]));
//        }

    }
}
