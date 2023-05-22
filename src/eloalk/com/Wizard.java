package eloalk.com;

import exceptions.InvalidHealthException;

public class Wizard extends Character {
    private char pawn = 'V';

    public Wizard(int activeField) {
        super(activeField);
    }

    public Wizard(int health, int activeField) throws InvalidHealthException {
        super(health, activeField);
    }

    public char getPawn() {
        return this.pawn;
    }
}
