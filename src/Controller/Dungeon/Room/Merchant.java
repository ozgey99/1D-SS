package Controller.Dungeon.Room;

import Models.Cards.AbstractCard;
import Models.Cards.CardColor;
import Controller.Dungeon.AbstractRoom;
import Models.Creatures.Pet;
import Models.Object.AbstractRelic;
import Models.Utils;
import View.Main;
import View.MerchantScene;
import javafx.scene.media.MediaPlayer;


import java.util.ArrayList;

import static View.Main.game;

public class Merchant extends AbstractRoom {
    ArrayList<AbstractCard> cards;
    ArrayList<Integer> cardPrices;
    ArrayList<AbstractRelic> relics;
    ArrayList<Integer> relicPrices;
    Pet pet;
    int petPrice;
    MediaPlayer mediaPlayer;

    public Merchant(ArrayList<AbstractRoom> c) {
        type = RoomType.SHOP;
        children = c;
        done = false;
        cards = new ArrayList<>();
        cardPrices = new ArrayList<>();
        relics = new ArrayList<>();
        relicPrices = new ArrayList<>();
        petPrice = 100;

    }

    public void generate() {
        if(game.getPlayer().getPet() == null){
            pet = new Pet();
        }

        ArrayList<AbstractCard> allCards = Utils.getAllCardsOfColor(CardColor.RED);

        for (AbstractCard y : game.getPlayer().masterDeck.getCardList()) {
            allCards.removeIf(c -> y.getName() == c.getName());
        }

        while (allCards.size() > 0 && cards.size() != 5) {
            int ind = (int) (Math.random() * (allCards.size() - 1));
            AbstractCard temp = allCards.get(ind);
            cards.add(temp);
            allCards.remove(ind);
            cardPrices.add((int) (Math.random() * 250));
        }

        ArrayList<AbstractRelic> allRelics = Utils.getAllRelics();

        for (AbstractRelic y : game.getPlayer().relics) {
            allRelics.removeIf(r -> y.getName() == r.getName());
            System.out.println("duplicate relic" + y.getName());
        }


        while (allRelics.size() > 0 && relics.size() != 4) {
            int ind = (int) (Math.random() * (allRelics.size() - 1));
            AbstractRelic temp = allRelics.get(ind);
            relics.add(temp);
            allRelics.remove(ind);

            switch (temp.getRarity()){
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

        Main.currentScene = new MerchantScene();
        Main.window.setScene(
                Main.currentScene);

        System.out.println(" I AM IN MERCHANT ROOM");

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

    public Pet getPet(){
        return pet;
    }

    public int getPetPrice(){
        return petPrice;
    }
}
