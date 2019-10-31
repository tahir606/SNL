import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JPanel board = createBoard();

        f.add(title, BorderLayout.NORTH);
        f.add(restartBtn, BorderLayout.SOUTH);
        f.add(rollDiceBtn, BorderLayout.EAST);
        f.add(noticeBoardLbl, BorderLayout.WEST);
        f.add(board, BorderLayout.CENTER);

        f.setSize(600, 600);
        f.setVisible(true);
    }

    int row = 5, col = 6;
    int matrix[][] = new int[row][col];

    private JPanel createBoard() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(row, col));

        int i = 30;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
//                System.out.println("Row: " + r + " Col: " + c);
                Tile tile = new Tile(r, c, matrix);
                tile.setText(String.valueOf(i));
                p.add(tile);
                i--;
            }
        }
        return p;
    }

    public static class Tile extends JButton {

        private final int[][] fModel;
        private final int fX;
        private final int fY;

        public Tile(final int x, final int y, final int[][] model) {
            fX = x;
            fY = y;
            fModel = model;

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fModel[fX][fY] = fModel[fX][fY] == 1 ? 0 : 1;
                    updateNameFromModel();
                }
            });
            updateNameFromModel();
        }

        private void updateNameFromModel() {
            setText(String.valueOf(fModel[fX][fY]));
        }

    }
}
