package eloalk.com;

public class Warrior extends Character {
    private char pawn = 'H';

    public Warrior(int activeField) {
        super(activeField);
    }

    public char getPawn() {
        return this.pawn;
    }
}
