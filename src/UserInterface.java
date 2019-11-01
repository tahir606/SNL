import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A Boundary Class which deals only with the User Interface
public class UserInterface {

    public volatile static boolean paceClick = false;

    private JFrame frame = new JFrame();
    public JPanel boardPanel;

    private JLabel noticeBoardLbl = new JLabel("Notice Board");
    private JLabel title = new JLabel("Snakes And Ladders");
    public JButton restartBtn = new JButton("Restart");
    public JButton rollDiceBtn = new JButton("Roll Dice");

    public static final int[] ladderStarts = {4, 12};
    public static final int[] ladderEnds = {14, 22};

    public static final int[] snakeStarts = {20, 16};
    public static final int[] snakeEnds = {7, 5};

    public UserInterface() {
        createScreen();
    }

    //Using Border Layout Manager
    private void createScreen() {

        //Notice Board
        noticeBoardLbl.setText("Notifications will be displayed here");

        //Center
        JPanel board = createBoard();

        frame.add(title, BorderLayout.NORTH);
        frame.add(noticeBoardLbl, BorderLayout.SOUTH);
        frame.add(rollDiceBtn, BorderLayout.EAST);
        frame.add(restartBtn, BorderLayout.WEST);
        frame.add(board, BorderLayout.CENTER);

        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    int row = 5, col = 6;
    int matrix[][] = new int[row][col];

    private JPanel createBoard() {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(row, col));

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
                Tile tile = new Tile(r, c, matrix, i, this);
                if (r % 2 == 0) {
                    i++;
                } else {
                    i--;
                }
                boardPanel.add(tile);
            }
        }
        return boardPanel;
    }

    public void setNoticeBoardTxt(String text) {
        noticeBoardLbl.setText(text);
    }

    public String getNoticeBoardTxt() {
        return noticeBoardLbl.getText();
    }

    public void removeLabel(int p) {
        Component[] components = boardPanel.getComponents();

        if (p == 1) {
            int index = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] instanceof JButton) {
                    JButton btn = (JButton) components[i];
                    if (((Integer) btn.getClientProperty("id")) == Game.prevPlayerPosition) {
                        index = i;
                        break;
                    }
                }
            }
            if (index > -1) {
                ((JButton) boardPanel.getComponent(index)).setText(((JButton) boardPanel.getComponent(index)).getText().replace("Y", ""));
            }
        } else if (p == 2) {
            int index = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] instanceof JButton) {
                    JButton btn = (JButton) components[i];
                    if (((Integer) btn.getClientProperty("id")) == Game.prevComputerPosition) {
                        index = i;
                        break;
                    }
                }
            }
            if (index > -1) {
                ((JButton) boardPanel.getComponent(index)).setText(((JButton) boardPanel.getComponent(index)).getText().replace("C", ""));
            }
        }
    }

    private boolean checkForSnakesAndLadders(int i) {
        int score, beforeScore = 0;
        if (i == 1) {
            score = Game.playerPosition;
            beforeScore = score;
        } else
            score = Game.computerPosition;

        System.out.println("Score: " + score);

        for (int j = 0; j < 2; j++) {
            //If at ladder start
            if (score == UserInterface.ladderStarts[j]) {
                System.out.println("At Ladder Start");
                score = UserInterface.ladderEnds[j];
                break;
            }

            if (score == UserInterface.snakeStarts[j]) {
                System.out.println("At Snake Start");
                score = UserInterface.snakeEnds[j];
                break;
            }
        }

        System.out.println("Before : " + score);
        if (i == 1)
            Game.playerPosition = score;
        else
            Game.computerPosition = score;
        System.out.println("After : " + Game.playerPosition);
        if (Game.playerPosition < beforeScore || Game.playerPosition > beforeScore) {
            System.out.println("After 2: " + score);
            return true;
        } else {
            return false;
        }
    }

    private void checkForWin(int i) {
        int score;
        if (i == 1) {
            score = Game.playerPosition;
        } else {
            score = Game.computerPosition;
        }

        if (score == 30) {
            if (i == 1)
                setNoticeBoardTxt("You Win!");
            else
                setNoticeBoardTxt("Computer Wins!");
        } else {
            if (i == 1)
                Game.playerPosition = (Game.prevPlayerPosition != 0 ? Game.prevPlayerPosition : Game.playerPosition);
            else
                Game.computerPosition = (Game.prevComputerPosition != 0 ? Game.prevComputerPosition : Game.computerPosition);
        }
    }

    private void movePlayer() {
        Component[] components = boardPanel.getComponents();
        int index = 0;
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JButton) {
                JButton btn = (JButton) components[i];
                if (((Integer) btn.getClientProperty("id")) == Game.playerPosition) {
                    index = i;
                    break;
                }
            }
        }
        System.out.println("Moving Player to: " + ((JButton) boardPanel.getComponent(index)).getText());
        ((JButton) boardPanel.getComponent(index)).setText(((JButton) boardPanel.getComponent(index)).getText() + " Y ");
    }

    public static class Tile extends JButton {

        private final int[][] fModel;
        private final int fX;
        private final int fY;

        private final int no;

        private final UserInterface UI;

        public Tile(final int x, final int y, final int[][] model, final int no, UserInterface ui) {
            fX = x;
            fY = y;
            fModel = model;
            this.no = no;
            UI = ui;

            putClientProperty("id", no);

            String lbl = String.valueOf(no);

            for (int i = 0; i < 2; i++) {
                //Ladder Starts
                if (no == UI.ladderStarts[i]) {
                    putClientProperty("Prop", "LS");    //Ladder Starts
                    lbl = lbl + " LS ";
                }
                if (no == UI.ladderEnds[i]) {
                    putClientProperty("Prop", "LE");    //Ladder Ends
                    lbl = lbl + " LE ";
                }
                if (no == UI.snakeStarts[i]) {
                    putClientProperty("Prop", "SS");    //Snake Starts
                    lbl = lbl + " SS ";
                }
                if (no == UI.snakeEnds[i]) {
                    putClientProperty("Prop", "SE");    //Snake Ends
                    lbl = lbl + " SE ";
                }
            }

            setText(lbl);


            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Game.playerTurn) {
                        Object property = getClientProperty("id");
                        if (property instanceof Integer) {
                            int objectCounter = ((Integer) property);
                            System.out.println(Game.playerPosition);
                            if (objectCounter == Game.playerPosition) {
                                ui.removeLabel(1);
                                setText(getText() + " Y");
                                UserInterface.paceClick = true;
//                                if (UI.checkForSnakesAndLadders(1)) {
//                                    System.out.println("Moving Player");
//                                    UI.movePlayer();
//                                }
//                                UI.checkForWin(1);
                            } else {
                                ui.setNoticeBoardTxt("Please Click the Correct Button");
                            }

                        }
                    }
                }
            });
        }

    }
}
