package eloalk.com;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        //alapfeladat teljes
        Character wizard = new Character('V', 3);
        Character warrior = new Character('H', 1);
        int round = 0;

        Scanner scanner = new Scanner(System.in);

        String input = null;
        do {
            int wizardNextPosition = ThreadLocalRandom.current().nextInt(1, 4);
            int warriorNextPosition = ThreadLocalRandom.current().nextInt(1, 4);
            char[] arena = new char[3];
            String harcString = "";

            if (round >= 1) {
                wizard.setActiveField(wizardNextPosition);
                warrior.setActiveField(warriorNextPosition);
            }

            int wizardPosition = wizard.getActiveField();
            int warriorPosition = warrior.getActiveField();

            if (wizardPosition == warriorPosition) {
                arena[wizardPosition - 1] = 'X';
            } else {
                arena[wizardPosition - 1] = 'V';
                arena[warriorPosition - 1] = 'H';
            }

            for (int i = 0; i <= 2; i++) {
                if (arena[i] != 'V' && arena[i] != 'H' && arena[i] != 'X') {
                    arena[i] = '_';
                }
            }


            if (wizardPosition == warriorPosition) {
                harcString = "harc: ";
                warrior.setHealth(warrior.getHealth() - ThreadLocalRandom.current().nextInt(1, 7));
                wizard.setHealth(wizard.getHealth() - ThreadLocalRandom.current().nextInt(1, 7));
            }


            System.out.println(arena[0] + "" + arena[1] + "" + arena[2] + " --> " + harcString +
                    "H:" + warrior.getHealth() + ", " + "V:" + wizard.getHealth());

            if (warrior.getHealth() == 0 || wizard.getHealth() == 0) {
                if (warrior.getHealth() > 0) {
                    System.out.println("Harcos győzött\n");
                } else if (wizard.getHealth() > 0) {
                    System.out.println("Varázsló győzött\n");
                }
                else {
                    System.out.println("Döntetlen");
                }

                System.out.println("Játsszunk egy újabb játékot? (Y/N)");
                if (scanner.nextLine().toLowerCase().equals("n")) {
                    break;
                } else {
                    wizard = new Character('V', 3);
                    warrior = new Character('H', 1);
                    arena = new char[3];
                    round = 0;
                    System.out.println('\n');

                    continue;
                }

            }

            System.out.println("Következő kör? (Y/N)");
            round++;

            input = scanner.nextLine().toLowerCase();
        } while (!input.equals("n"));

        System.out.println("Játék vége");
    }
}