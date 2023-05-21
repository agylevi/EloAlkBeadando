package eloalk.com;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import exceptions.OutOfArenaException;

public class GameEngine {
    private Arena arena;
    private Warrior warrior;
    private Wizard wizard;
    private boolean warriorWon = false;
    private boolean wizardWon = false;
    int round = 0;


    public GameEngine(Arena arena, Warrior warrior, Wizard wizard) {
        this.arena = arena;
        this.warrior = warrior;
        this.wizard = wizard;
    }

    public int getRound() {
        return this.round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public RoundData play() throws OutOfArenaException {
        int wizardNextPosition = ThreadLocalRandom.current().nextInt(1, 4);
        int warriorNextPosition = ThreadLocalRandom.current().nextInt(1, 4);

        if (warriorNextPosition > arena.getArenaSize() || warriorNextPosition < 0 ||
                wizardNextPosition > arena.getArenaSize() ||  wizardNextPosition < 0) {
            throw new OutOfArenaException("A beállított mező kívül esik a játéktéren.");
        }

        if (round >= 1) {
            wizard.setActiveField(wizardNextPosition);
            warrior.setActiveField(warriorNextPosition);
        }

        if (wizard.getActiveField() == warrior.getActiveField()) {
            warrior.setHealth(warrior.getHealth() - ThreadLocalRandom.current().nextInt(1, 7));
            wizard.setHealth(wizard.getHealth() - ThreadLocalRandom.current().nextInt(1, 7));
        }

        arena.setArena(warrior, wizard);
        String arenaState = arena.printArena(warrior, wizard);

        if (warrior.getHealth() == 0 || wizard.getHealth() == 0) {
            if (warrior.getHealth() > 0) {
                warriorWon = true;
                System.out.println("Harcos győzött\n");
            } else if (wizard.getHealth() > 0) {
                wizardWon = true;
                System.out.println("Varázsló győzött\n");
            }
            else {
                warriorWon = true;
                wizardWon = true;
                System.out.println("Döntetlen");
            }

            wizard = new Wizard(3);
            warrior = new Warrior(1);
            arena = new Arena(3);
            round = 0;
            System.out.println('\n');
        }

        round++;

        return new RoundData(wizard.getHealth(), warrior.getHealth(), warriorWon, wizardWon, arena.getArena(), arenaState);
    }

    public RoundData advanceRound() throws OutOfArenaException {
        int wizardNextPosition = ThreadLocalRandom.current().nextInt(1, 4);
        int warriorNextPosition = ThreadLocalRandom.current().nextInt(1, 4);

        if (warriorNextPosition > arena.getArenaSize() || warriorNextPosition < 0 ||
                wizardNextPosition > arena.getArenaSize() ||  wizardNextPosition < 0) {
            throw new OutOfArenaException("A beállított mező kívül esik a játéktéren.");
        }

        if (round >= 1) {
            wizard.setActiveField(wizardNextPosition);
            warrior.setActiveField(warriorNextPosition);
        }

        if (wizard.getActiveField() == warrior.getActiveField()) {
            warrior.setHealth(warrior.getHealth() - ThreadLocalRandom.current().nextInt(1, 7));
            wizard.setHealth(wizard.getHealth() - ThreadLocalRandom.current().nextInt(1, 7));
        }

        arena.setArena(warrior, wizard);
        String arenaState = arena.printArena(warrior, wizard);

        if (warrior.getHealth() == 0 || wizard.getHealth() == 0) {
            if (warrior.getHealth() > 0) {
                warriorWon = true;
                System.out.println("Harcos győzött\n");
                arenaState += '\n' + "Harcos győzött\n";
            } else if (wizard.getHealth() > 0) {
                wizardWon = true;
                System.out.println("Varázsló győzött\n");
                arenaState += '\n' + "Varázsló győzött\n";
            }
            else {
                warriorWon = true;
                wizardWon = true;
                System.out.println("Döntetlen");
                arenaState += '\n' + "Döntetlen\n";
            }
        }

        round++;

        return new RoundData(wizard.getHealth(), warrior.getHealth(), warriorWon, wizardWon, arena.getArena(), arenaState);
    }
}
