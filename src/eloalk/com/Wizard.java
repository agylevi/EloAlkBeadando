package eloalk.com;

public class Wizard extends Character {
    private char pawn = 'V';

    public Wizard(int activeField) {
        super(activeField);
    }

    public char getPawn() {
        return this.pawn;
    }
}
