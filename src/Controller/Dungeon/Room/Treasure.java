package Controller.Dungeon.Room;

import Models.Actions.RelicActions;
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
        type = RoomType.CHEST;
        children = c;
        done = false;
        relics = new ArrayList<>();
        cards = new ArrayList<>();
        relicReward();
        cardReward();
    }

    // created relic list (contain 1) goldAmount is determined
    private void relicReward() {
        ArrayList<AbstractRelic> allRelics = Utils.getAllRelics();
        Collections.shuffle(allRelics);
        for (AbstractRelic r : allRelics) {
            if (!Utils.containsInstance(Main.game.getPlayer().relics, r.getClass())) {
                relics.add(r);
                break;
            }
        }

        goldAmount = (int) (Math.random() * 100) + 10;
    }

    private void cardReward() {
        ArrayList<AbstractCard> allCards = Utils.getAllCardsOfColor(CardColor.RED);
        Collections.shuffle(allCards);
        for (AbstractCard c : allCards) {
            if (!Utils.containsInstance(game.getPlayer().masterDeck.getCardList(), c.getClass())) {
                cards.add(c);
                break;
            }
        }
    }

    @Override
    public void start() {

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
