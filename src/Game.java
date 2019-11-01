import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Controller Class
public class Game {

    public static int playerPosition = 0,
            computerPosition = 0;

    public static int prevPlayerPosition = 0,
            prevComputerPosition = 0;

    public static boolean playerTurn = false,
            computerTurn = false;

    private boolean gameStart = true;

    private int dice = 0;

    private UserInterface ui;

    public Game() {
        ui = new UserInterface();
        setRestartBtn();
        setRollDiceBtn();
        startGame();
    }

    public void startGame() {
        playerPosition = 0;
        computerPosition = 0;
        ui.setNoticeBoardTxt("To decide who plays first: Roll the dice");
        gameStart = true;
    }

    public void setRestartBtn() {
        ui.restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
    }

    public void setRollDiceBtn() {
        ui.rollDiceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dice = Dice.rollDice();
                //Deciding who plays first
                if (gameStart) {
                    int c = Dice.rollDice();
                    ui.setNoticeBoardTxt("You Rolled a: " + dice + " The Computer rolled a: " + c);
                    gameStart = false;
                    if (dice > c) {
                        ui.setNoticeBoardTxt(ui.getNoticeBoardTxt() + " You Play First! Roll The Dice");
                        playerTurn = true;
                    } else {
                        ui.setNoticeBoardTxt(ui.getNoticeBoardTxt() + " Computer Plays First! Roll The Dice");
                        computerTurn = true;
                    }
                } else {
                    if (playerTurn) {
                        playerTurn();
                    } else if (computerTurn) {
                        computerTurn();
                        removeLabel(2);
                    }
                }
            }
        });
    }

    private void playerTurn() {
        prevPlayerPosition = playerPosition;
        playerPosition = playerPosition + dice;
        ui.setNoticeBoardTxt("Please Move " + dice + " Paces! Computer's turn! Roll The Dice!");
        playerTurn = false;
        computerTurn = true;
//        if (checkForSnakesAndLadders(1))
//            movePlayer();
//        checkForWin(1);
//        System.out.println("PP: " + playerPosition);
    }

    private void computerTurn() {
        prevComputerPosition = computerPosition;
        computerPosition = computerPosition + dice;
        ui.setNoticeBoardTxt(ui.getNoticeBoardTxt() + " \n Computer Rolled " + dice + " Paces " + " Your Turn Now!");
//        checkForSnakesAndLadders(2);
        moveComputer();
        computerTurn = false;
        playerTurn = true;
//        checkForWin(2);
    }

//    private boolean checkForSnakesAndLadders(int i) {
//        int score;
//        if (i == 1)
//            score = playerPosition;
//        else
//            score = computerPosition;
//
//        System.out.println("Score: " + score);
//
//        for (int j = 0; j < 2; j++) {
//            //If at ladder start
//            if (score == UserInterface.ladderStarts[j]) {
//                score = UserInterface.ladderEnds[j];
//                break;
//            }
//
//            if (score == UserInterface.snakeStarts[j]) {
//                score = UserInterface.snakeEnds[j];
//                break;
//            }
//        }
//
//        System.out.println("Before : " + score);
//        if (i == 1)
//            playerPosition = score;
//        else
//            computerPosition = score;
//        System.out.println("After : " + playerPosition);
//        if (playerPosition < score || playerPosition > score) {
//            System.out.println("After 2: " + score);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private void checkForWin(int i) {
//        int score;
//        if (i == 1) {
//            score = playerPosition;
//        } else {
//            score = computerPosition;
//        }
//
//        if (score == 30) {
//            if (i == 1)
//                ui.setNoticeBoardTxt("You Win!");
//            else
//                ui.setNoticeBoardTxt("Computer Wins!");
//        } else {
//            if (i == 1)
//                playerPosition = (prevPlayerPosition != 0 ? prevPlayerPosition : playerPosition);
//            else
//                computerPosition = (prevComputerPosition != 0 ? prevComputerPosition : computerPosition);
//        }
//    }

    private void moveComputer() {
        Component[] components = ui.boardPanel.getComponents();
        int index = 0;
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JButton) {
                JButton btn = (JButton) components[i];
                if (((Integer) btn.getClientProperty("id")) == computerPosition) {
                    index = i;
                    break;
                }
            }
        }
        ((JButton) ui.boardPanel.getComponent(index)).setText(((JButton) ui.boardPanel.getComponent(index)).getText() + " C ");
    }

    private void movePlayer() {
        Component[] components = ui.boardPanel.getComponents();
        int index = 0;
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JButton) {
                JButton btn = (JButton) components[i];
                if (((Integer) btn.getClientProperty("id")) == playerPosition) {
                    index = i;
                    break;
                }
            }
        }
        ((JButton) ui.boardPanel.getComponent(index)).setText(((JButton) ui.boardPanel.getComponent(index)).getText() + " Y ");
    }

    private void removeLabel(int p) {
        Component[] components = ui.boardPanel.getComponents();

        if (p == 1) {
            int index = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] instanceof JButton) {
                    JButton btn = (JButton) components[i];
                    if (((Integer) btn.getClientProperty("id")) == prevPlayerPosition) {
                        index = i;
                        break;
                    }
                }
            }
            if (index > -1) {
                ((JButton) ui.boardPanel.getComponent(index)).setText(((JButton) ui.boardPanel.getComponent(index)).getText().replace("Y", ""));
            }
        } else if (p == 2) {
            int index = -1;
            for (int i = 0; i < components.length; i++) {
                if (components[i] instanceof JButton) {
                    JButton btn = (JButton) components[i];
                    if (((Integer) btn.getClientProperty("id")) == prevComputerPosition) {
                        index = i;
                        break;
                    }
                }
            }
            if (index > -1) {
                ((JButton) ui.boardPanel.getComponent(index)).setText(((JButton) ui.boardPanel.getComponent(index)).getText().replace("C", ""));
            }
        }
    }


}
