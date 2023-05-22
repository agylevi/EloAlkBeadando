package eloalk.com;

import exceptions.InvalidHealthException;

public class Warrior extends Character {
    private char pawn = 'H';

    public Warrior(int activeField) {
        super(activeField);
    }

    public Warrior (int health, int activeField) throws InvalidHealthException {
        super(health, activeField);
    }

    public char getPawn() {
        return this.pawn;
    }
}
