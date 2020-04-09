package Models;


import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Creatures.Monsters.MonsterMove;
import Models.Dungeon.Dungeon;
import Models.Dungeon.Room.Fight;

import java.util.Scanner;

public class UI {

    public static void displayStartMessage() {
        System.out.println("The game has started.");
    }

    public static void displayFinishMessage(AbstractCharacter player) {
        if (player.getCurrentHP() > 0) {
            System.out.println("You have won!");
        } else {
            System.out.println("You have lost!");
        }
    }

    public static void displayFightStart(Fight f) {
        System.out.println("Fight begins!");
    }

    public static void displayFightInfo(Fight f) {
        AbstractCharacter player = Main.game.getPlayer();
        System.out.println("Player Health: " + player.getCurrentHP() + "/" + player.getMaxHP());
        System.out.println("Player Block: " + player.getBlock());
        System.out.println("Player Energy: " + player.currentEnergy);
        for (int i = 0; i < f.getMonsters().size(); i++) {
            System.out.print("" + i + " - ");
            displayMonster(f.getMonsters().get(i));
            System.out.println("\n\t ---");
        }
    }

    public static void displayMonster(AbstractMonster m) {
        System.out.println(m.getName() + "(" + m.getCurrentHP() + "/" + m.getMaxHP() + ")");
        System.out.println("\tCurrent Block: " + m.getBlock());
        System.out.print("\t Next move: ");

        MonsterMove move = m.getNextMove();
        if (move.getDamage() != 0) {
            System.out.print("attack(" + move.getDamage() + ") ");
        }

        if (move.getBlock() != 0) {
            System.out.print("block(" + move.getBlock() + ") ");
        }
    }

    public static void displayDeck(Deck d, String name) {
        int size = d.getSize();
        System.out.println(name + " contains " + size + " cards.");
        for (int i = 0; i < size; i++) {
            System.out.println(i);
            AbstractCard c = d.getCard(i);
            displayCard(c);
        }
    }

    public static void displayCard(AbstractCard c) {
        System.out.println("---- " + c.getName() + "(" + c.getCost() + ")" + " ----");
        System.out.println("\t" + c.getDescription());
    }

    public static void displayAct(Dungeon d) {
        String act = "Act " + d.getAct() + "\n" + d.getName();
        System.out.println(act);
    }

    public static int getInput(int min, int max) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        while (i >= max || i < min) {
            System.out.println("Input is out of parameters. Must be between " + min + " and " + max);
            i = in.nextInt();
        }

        return i;
    }
}
