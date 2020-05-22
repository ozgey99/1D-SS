package Controller.Dungeon.Room;

import Models.Cards.AbstractCard;
import Models.Cards.CardColor;
import Controller.Dungeon.AbstractRoom;
import Models.Object.AbstractRelic;
import Models.Utils;
import View.Main;
import View.TreasureScene;
import javafx.scene.media.MediaPlayer;


import java.util.ArrayList;
import java.util.Collections;

import static View.Main.game;

public class Treasure extends AbstractRoom {
    ArrayList<AbstractRelic> relics;
    ArrayList<AbstractCard> cards;
    int goldAmount;
    MediaPlayer mediaPlayer;

    public Treasure(ArrayList<AbstractRoom> c) {
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
        Main.currentScene = new TreasureScene();
        Main.window.setScene(
                Main.currentScene);
        Main.currentScene.initialize();
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
