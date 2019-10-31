import java.util.Random;

//Entity Class
public class Dice {

    public Dice() {

    }

    public static int rollDice() {
        return genRandom(6);
    }

    private static int genRandom(int size) {
        Random ran = new Random();
        int range = size;
        return ran.nextInt(range) + 1;
    }


}
