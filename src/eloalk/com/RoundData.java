package eloalk.com;

import java.io.Serializable;

public class RoundData implements Serializable {
    private int wizardHealth;
    private int warriorHealth;
    private int wizardPosition;
    private int warriorPosition;
    private int round;
    private boolean warriorWon;
    private boolean wizardWon;
    private char[] arenaState;

    private String arenaStateString;

    public RoundData(int wizardHealth, int warriorHealth, int wizardPosition, int warriorPosition, int round, boolean warriorWon, boolean wizardWon, char[] arenaState, String arenaStateString) {
        this.wizardHealth = wizardHealth;
        this.warriorHealth = warriorHealth;
        this.wizardPosition = wizardPosition;
        this.warriorPosition = wizardPosition;
        this.round  = round;
        this.warriorWon = warriorWon;
        this.wizardWon = wizardWon;
        this.arenaState = arenaState;
        this.arenaStateString = arenaStateString;
    }

    public char[] getArenaState() {
        return arenaState;
    }

    public int getWizardHealth() {
        return wizardHealth;
    }

    public int getWarriorHealth() {
        return warriorHealth;
    }

    public boolean isWarriorWon() {
        return warriorWon;
    }

    public boolean isWizardWon() {
        return wizardWon;
    }

    public String getArenaStateString() {
        return arenaStateString;
    }

    public int getWizardPosition() {
        return wizardPosition;
    }

    public int getWarriorPosition() {
        return warriorPosition;
    }

    public int getRound() {
        return round;
    }
}
