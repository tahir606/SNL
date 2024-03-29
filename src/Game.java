import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Controller Class
public class Game {

    public static int playerPosition = 0, computerPosition = 0;

    public static int prevPlayerPosition = 0, prevComputerPosition = 0;

    public static boolean playerTurn = false, computerTurn = false;

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
        if ((playerPosition + dice) < 30) playerPosition = playerPosition + dice;
        ui.setNoticeBoardTxt("Please Move " + dice + " Paces! Computer's turn! Roll The Dice!");
        playerTurn = false;
        computerTurn = true;
    }

    private void computerTurn() {
        prevComputerPosition = computerPosition;
        if ((computerPosition + dice) <= 30) computerPosition = computerPosition + dice;
        ui.setNoticeBoardTxt(ui.getNoticeBoardTxt() + " \n Computer Rolled " + dice + " Paces " + " Your Turn Now!");
        checkForSnakesAndLadders();
        moveComputer();
        computerTurn = false;
        playerTurn = true;
        checkForWin();
    }

    private void checkForSnakesAndLadders() {
        int score, beforeScore = 0;
        score = Game.computerPosition;
        beforeScore = score;

        System.out.println("Score: " + score);

        for (int j = 0; j < 2; j++) {
            //If at ladder start
            if (score == UserInterface.ladderStarts[j]) {
                ui.setNoticeBoardTxt(ui.getNoticeBoardTxt() + " Computer landed on a Ladder!");
                score = UserInterface.ladderEnds[j];
                break;
            }

            if (score == UserInterface.snakeStarts[j]) {
                ui.setNoticeBoardTxt(ui.getNoticeBoardTxt() + " Computer landed on a Snake!");
                score = UserInterface.snakeEnds[j];
                break;
            }
        }

        System.out.println("Before : " + score);
        Game.computerPosition = score;
        System.out.println("After : " + Game.computerPosition);
//        if (Game.playerPosition < beforeScore || Game.playerPosition > beforeScore) {
//            System.out.println("After 2: " + score);
//            return true;
//        } else {
//            return false;
//        }
    }

    private void checkForWin() {
        int score = computerPosition;

        if (score == 30) {
            ui.setNoticeBoardTxt("Computer Wins!");
        } else if (score > 30) {
            playerPosition = prevComputerPosition;
        }
    }

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
        if (!((JButton) ui.boardPanel.getComponent(index)).getText().contains("C"))
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
