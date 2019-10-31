import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Controller Class
public class Game {

    private int playerPosition = 0,
            computerPosition = 0;

    private UserInterface ui;

    public Game() {
        ui = new UserInterface();
        ui.restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Game Again");
                playerPosition = 0;
                computerPosition = 0;
                ui.setNoticeBoardTxt("To decide who plays first: Roll the dice");
//                int p = waitForDiceRoll(); //THIS IS MAKING THE SHIT HANG CORRECT THIS
                int c = Dice.rollDice();
//                startGame();
            }
        });
        startGame();
    }

    private void computerTurn() {
        int c = Dice.rollDice();
        ui.setNoticeBoardTxt(ui.getNoticeBoardTxt() + " Computer Rolls a " + c);
        computerPosition = computerPosition + c;
    }

    public void startGame() {
        playerPosition = 0;
        computerPosition = 0;
        ui.setNoticeBoardTxt("To decide who plays first: Roll the dice");
        int p = waitForDiceRoll();
        int c = Dice.rollDice();
        ui.setNoticeBoardTxt("You Rolled: " + p + " Computer Rolled: " + c);
        //User rolled greater
        if (UserInterface.diceRoll > c) {
            ui.setNoticeBoardTxt(ui.getNoticeBoardTxt() + " Your turn First! Please roll Dice");
            playerTurn();
        } else {
            ui.setNoticeBoardTxt(ui.getNoticeBoardTxt() + " Computer Plays First!");
            computerTurn();
        }
    }

    private void playerTurn() {
        int p = waitForDiceRoll();
        ui.setNoticeBoardTxt(" You Rolled a " + p);
        computerPosition = computerPosition + p;
    }

    private int waitForDiceRoll() {
        UserInterface.diceClick = false;
        while (!UserInterface.diceClick) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
        return UserInterface.diceRoll;
    }

}
