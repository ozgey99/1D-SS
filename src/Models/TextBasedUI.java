package Models;


import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Creatures.Monsters.MonsterMove;
import Models.Dungeon.Dungeon;
import Models.Dungeon.Room.Fight;
import Models.Dungeon.Room.Merchant;
import Models.Dungeon.Room.Treasure;
import Models.Object.AbstractRelic;

import java.util.Scanner;

public class TextBasedUI {

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
        System.out.println("Player block: " + player.getBlock());
        System.out.println("Player Energy: " + player.currentEnergy);
        for (int i = 0; i < f.getMonsters().size(); i++) {
            System.out.print("" + i + " - ");
            displayMonster(f.getMonsters().get(i));
            System.out.println("\n\t ---");
        }
    }

    public static void displayMonster(AbstractMonster m) {
        System.out.println(m.getName() + "(" + m.getCurrentHP() + "/" + m.getMaxHP() + ")");
        System.out.println("\tCurrent block: " + m.getBlock());
        System.out.print("\t Next move: " );

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
        System.out.println(c == null);
        System.out.println("---- " + c.getName() + "(" + c.getCost() + ")" + " ----");
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

    public static void displayRestRoom() {
        System.out.println("Choose one:");
        System.out.println("0. Rest to Heal 30% of your Max HP.");
        System.out.println("1. Smith to upgrade a card in your deck.");
    }

    public static int displaySmithList(Deck masterDeck) {
        int amountNotUpgraded = 0;
        for (int i = 0; i < masterDeck.getSize(); i++) {
            AbstractCard c= masterDeck.getCard(i);
            if (c.isUpgradable()) {
                System.out.println(c.getName());
                System.out.println("\t" + c.getDescription());
                amountNotUpgraded++;
            }
        }

        return amountNotUpgraded;
    }

    public static int displayTreasure(Treasure t) {
        for (AbstractRelic r : t.getRelics()) {
            System.out.println(r.getName());
            System.out.println("\t" + r.getDescription());
        }

        int res = t.getRelics().size() - 1;

        if (t.getGoldAmount() != 0) {
            System.out.println(t.getGoldAmount() + " Gold.");
            res++;
        }

        return res;

    }

    public static int displayMerchant(Merchant m) {
        int i;
        for (i = 0; i < m.getCards().size(); i++) {
            System.out.println(m.getCards().get(i).getName() + "(" + m.getPrices().get(i) + " Gold)");
        }

        return i;
    }
}
