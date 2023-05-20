package eloalk.com;

import exceptions.OutOfArenaException;

public class Main {
    public static void main(String[] args) {
        Arena arena = new Arena(3);
        Warrior warrior = new Warrior(1);
        Wizard wizard = new Wizard(3);

        GameEngine game = new GameEngine(arena, warrior, wizard);

        try {
            game.play();
        } catch (OutOfArenaException ex){
            System.out.println(ex.getMessage());
        }

    }
}