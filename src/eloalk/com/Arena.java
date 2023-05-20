package eloalk.com;

public class Arena {
    private char[] arena;
    private int arenaSize;

    public int getArenaSize() {
        return arenaSize;
    }

    public Arena(int arenaSize) {
        this.arena = new char[arenaSize];
        this.arenaSize = arenaSize;
    }


    public char[] getArena() {
        return arena;
    }

    public void setArena(Warrior warrior, Wizard wizard) {
        this.arena = new char[arenaSize];

        if (warrior.getActiveField() == wizard.getActiveField()) {
            this.arena[wizard.getActiveField() - 1] = 'X';
        } else {
            this.arena[wizard.getActiveField() - 1] = 'V';
            this.arena[warrior.getActiveField() - 1] = 'H';
        }

        for (int i = 0; i <= 2; i++) {
            if (this.arena[i] != 'V' && this.arena[i] != 'H' && this.arena[i] != 'X') {
                this.arena[i] = '_';
            }
        }
    }

    public void printArena(Warrior warrior, Wizard wizard) {
        String harcString = warrior.getActiveField() == wizard.getActiveField() ? " harc: " : " ";
        String arenaString = "";

        for (int i = 0; i < arenaSize; i++) {
            arenaString += this.arena[i];

            if (i != arenaSize - 1) {
                arenaString += "";
            }

            if (i == arenaSize - 1) {
                arenaString += " -->";
            }
        }

        System.out.println(arenaString + harcString +
                "H:" + warrior.getHealth() + ", " + "V:" + wizard.getHealth());
    }
}
