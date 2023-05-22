package eloalk.com;
import exceptions.InvalidHealthException;

import java.util.concurrent.ThreadLocalRandom;

public class Character {
    private int health;
    private int activeField;

    public Character(int activeField) {
        this.health = ThreadLocalRandom.current().nextInt(1, 7) + 3;
        this.activeField = activeField;
    }

    public Character(int health, int activeField) throws InvalidHealthException {
        if (health < 0) {
            throw new InvalidHealthException("Az életerő nem lehet negatív");
        }

        this.health = health;
        this.activeField = activeField;
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
}
