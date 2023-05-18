package eloalk.com;
import java.util.concurrent.ThreadLocalRandom;

public class Character {
    private int health;
    private int activeField;
    private char pawn;

    public Character(char pawn, int activeField) {
        this.health = ThreadLocalRandom.current().nextInt(1, 7) + 3;
        this.activeField = activeField;
        this.pawn = pawn;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        }
        else {
            this.health = health;
        }
    }

    public int getActiveField() {
        return activeField;
    }


    public void setActiveField(int activeField) {
        this.activeField = activeField;
    }

    public char getPawn() {
        return pawn;
    }
}
