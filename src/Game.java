
//Controller Class
public class Game {

    private int playerPosition = 0,
            computerPosition = 0;

    private boolean playerTurn = false,
            computerTurn = false;

    private UserInterface ui;

    public Game() {
        ui = new UserInterface();
    }

    public void startGame() {
        ui.setNoticeBoardTxt("To decide who plays first: Roll the dice");
        waitForDiceRoll();
        int c = Dice.rollDice();
        ui.setNoticeBoardTxt("You Rolled: " + UserInterface.diceRoll + " Computer Rolled: " + c);
        //User rolled greater
        if (UserInterface.diceRoll > c) {
            playerTurn();
        } else {
            computerTurn();
        }
    }

    private void playerTurn() {

    }

    private void computerTurn() {
        
    }

    private void waitForDiceRoll() {
        while (!UserInterface.diceClick) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
    }

}
