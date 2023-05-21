package eloalk.com;

public class RoundData {
    private int wizardHealth;
    private int warriorHealth;
    private boolean warriorWon;
    private boolean wizardWon;
    private char[] arenaState;

    private String arenaStateString;

    public RoundData(int wizardHealth, int warriorHealth, boolean warriorWon, boolean wizardWon, char[] arenaState, String arenaStateString) {
        this.wizardHealth = wizardHealth;
        this.warriorHealth = warriorHealth;
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
}
