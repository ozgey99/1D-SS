package Controller.Dungeon.Room;

import Models.Cards.AbstractCard;
import Models.Cards.CardColor;
import Controller.Dungeon.AbstractRoom;
import Models.Object.AbstractRelic;
import Models.Utils;
import View.Main;
import View.MerchantScene;



import java.util.ArrayList;

import static View.Main.game;

public class Merchant extends AbstractRoom {
    ArrayList<AbstractCard> cards;
    ArrayList<Integer> cardPrices;
    ArrayList<AbstractRelic> relics;
    ArrayList<Integer> relicPrices;

    public Merchant(ArrayList<AbstractRoom> c) {
        type = RoomType.SHOP;
        children = c;
        done = false;
        cards = new ArrayList<>();
        cardPrices = new ArrayList<>();
        relics = new ArrayList<>();
        relicPrices = new ArrayList<>();
        generate();
    }

    private void generate() {
        ArrayList<AbstractCard> allCards = Utils.getAllCardsOfColor(CardColor.RED);

        int index = (int) (Math.random() * (allCards.size() - 1));
        cards.add(allCards.get(index));
        cardPrices.add((int) (Math.random() * 250));
        allCards.remove(index);

        while (cards.size() != 5 ) {
            index = (int) (Math.random() * (allCards.size() - 1));
            cards.add(allCards.get(index));
            cardPrices.add((int) (Math.random() * 250));
        }

        ArrayList<AbstractRelic> allRelics = Utils.getAllRelics();


        while (relics.size() != 4) {
            int ind = (int) (Math.random() * (allRelics.size() - 1));
            relics.add(allRelics.get(ind));
            allRelics.remove(ind);
            switch (allRelics.get(ind).getRarity()){
                case BOSS:
                    relicPrices.add((int) (Math.random() * 450));
                    break;
                case RARE:
                    relicPrices.add((int) (Math.random() * 400));
                    break;
                case SHOP:
                    relicPrices.add((int) (Math.random() * 370));
                    break;
                case EVENT:
                    relicPrices.add((int) (Math.random() * 350));
                    break;
                case COMMON:
                    relicPrices.add((int) (Math.random() * 200));
                    break;
                case SPECIAL:
                    relicPrices.add((int) (Math.random() * 550));
                    break;
                case STARTER:
                    relicPrices.add((int) (Math.random() * 150));
                    break;
                case UNCOMMON:
                    relicPrices.add((int) (Math.random() * 300));
                    break;
            }
        }

    }


    @Override
    public void start() {
        game.currentScene = new MerchantScene();
        Main.window.setScene(
                game.currentScene);

        game.currentScene.initialize();
        System.out.println(" I AM IN MERCHANT ROOM");
        /*AbstractCharacter player = Main.game.getPlayer();
        game.currentScene.initialize();
        System.out.println(" I AM IN MERCHANT ROOM");
        while (!done) {
            int max = TextBasedUI.displayMerchant(this);
            int choose = TextBasedUI.getInput(-1, max);
            if (choose == -1) done = true;
            else if (player.getGold() >= cardPrices.get(choose)) {
                    player.masterDeck.addCard(cards.get(choose));
                    cards.remove(choose);
                    cardPrices.remove(choose);
            }
            else if(player.getGold() >= relicPrices.get(choose)){
                RelicActions.addRelic(player, relics.get(choose));
                relics.remove(choose);
                relicPrices.remove(choose);
            }
            else {
                System.out.println("You don't have enough gold to purchase this item.");
            }
        }*/
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
        return done;
    }

    public ArrayList<AbstractCard> getCards() {
        return cards;
    }

    public ArrayList<Integer> getCardPrices() {
        return cardPrices;
    }

    public ArrayList<AbstractRelic> getRelics()
    { return relics; }

    public ArrayList<Integer> getRelicPrices() {
        return relicPrices;
    }
}
