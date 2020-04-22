package sts;

import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Creatures.Monsters.MonsterMove;
import Models.Dungeon.Dungeon;
import Models.Dungeon.Room.Fight;
import Models.Dungeon.Room.Merchant;
import Models.Dungeon.Room.Treasure;
import Models.Main;
import Models.Object.AbstractRelic;

import java.util.Scanner;

import static sts.Main.game;

public class Controller {

    //change these messages to text on the screen please
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

    public static AbstractMonster getMonsterInput() {
        return  ( (Fight) game.getDungeon().getCurrentRoom()).getMonsters().get(0);

        /*if(game.getDungeon().getCurrentRoom() instanceof Fight){
            for(AbstractMonster m :((Fight) game.getDungeon().getCurrentRoom()).getMonsters()){
                if(m.getSelected())
                    return m;
            }
            return null;
        }
        return null;

         */
    }

    public static AbstractCard getCardInput(){
        if(game.getDungeon().getCurrentRoom() instanceof Fight){
            for(AbstractCard c:((Fight) game.getDungeon().getCurrentRoom()).getHand().getCardList()){
                if(c.getSelected()) {
                    System.out.println("CARD SELECTED");
                    return c;
                }
            }
            return null;
        }
        return null;
    }

    //get the input from a button
    public static boolean getNextTurn(){

        return false;
    }

}