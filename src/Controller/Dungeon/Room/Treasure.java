package Controller.Dungeon.Room;

import Controller.Actions.RelicActions;
import Models.Cards.AbstractCard;
import Models.Cards.CardColor;
import Models.Creatures.AbstractCharacter;
import Controller.Dungeon.AbstractRoom;
import Models.Object.AbstractRelic;
import Models.Utils;
import View.Main;
import View.TreasureScene;


import java.util.ArrayList;
import java.util.Collections;

import static View.Main.game;

public class Treasure extends AbstractRoom {
    ArrayList<AbstractRelic> relics;
    ArrayList<AbstractCard> cards;
    int goldAmount;

    public Treasure(ArrayList<AbstractRoom> c) {
        System.out.println("TREASURE CALLED");
        type = RoomType.CHEST;
        children = c;
        done = false;
        relics = new ArrayList<>();
        cards = new ArrayList<>();
    }

    // created relic list (contain 1) goldAmount is determined
    public void relicReward() {

        ArrayList<AbstractRelic> allRelics = Utils.getAllRelics();
        Collections.shuffle(allRelics);
        for (AbstractRelic r : allRelics) {

            boolean exist = false;
            for (AbstractRelic y : game.getPlayer().relics) {

                if(y.getName() == r.getName())
                    exist = true;

            }
            if(!exist)
                relics.add(r);
        }

        goldAmount = (int) (Math.random() * 100) + 10;

    }


    public void cardReward() {
        ArrayList<AbstractCard> allCards = Utils.getAllCardsOfColor(CardColor.RED);
        Collections.shuffle(allCards);
        for (AbstractCard c : allCards) {
            boolean exist = false;
            for (AbstractCard y : game.getPlayer().masterDeck.getCardList()) {
                //System.out.println("MY  RELICS " + y.getName());

                if(y.getName() == c.getName())
                    exist = true;
            }
            if(!exist)
                cards.add(c);
        }
    }

    @Override
    public void start() {

        System.out.println("TREASURE START CALLED");

        relicReward();
        cardReward();
        game.currentScene = new TreasureScene();
        Main.window.setScene(
                game.currentScene);
        game.currentScene.initialize();
    }

    public void addRewards() {
        AbstractCharacter player = game.getPlayer();
        player.changeGold(goldAmount);
        for (AbstractRelic r : relics) {
            RelicActions.addRelic(player, r);
        }
    }

    @Override
    public RoomType getType() {
        return type;
    }

    @Override

    public ArrayList<AbstractRoom> getChildren() {
        return children;
    }

    @Override
    public boolean getDone() {
        return false;
    }

    public ArrayList<AbstractRelic> getRelics() {
        return relics;
    }

    public ArrayList<AbstractCard> getCards(){ return cards;}

    public int getGoldAmount() {
        return goldAmount;
    }
}
