package Models.Dungeon.Room;

import Models.Cards.AbstractCard;
import Models.Cards.CardColor;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.AbstractRoom;
import Models.TextBasedUI;
import Models.Utils;
import sts.Main;


import java.util.ArrayList;

public class Merchant extends AbstractRoom {
    ArrayList<AbstractCard> cards;
    ArrayList<Integer> prices;

    public Merchant(AbstractRoom c) {
        type = RoomType.SHOP;
        children = c;
        done = false;
        cards = new ArrayList<>();
        prices = new ArrayList<>();
        generate();
    }

    private void generate() {
        ArrayList<AbstractCard> allCards = Utils.getAllCardsOfColor(CardColor.RED);

        int index = (int) (Math.random() * (allCards.size() - 1));
        cards.add(allCards.get(index));
        prices.add((int) (Math.random() * 250));
        allCards.remove(index);

        while (cards.size() != 4) {
            index = (int) (Math.random() * (allCards.size() - 1));
            cards.add(allCards.get(index));
            prices.add((int) (Math.random() * 250));
        }
    }

    @Override
    public void start() {
        AbstractCharacter player = Main.game.getPlayer();

        while (!done) {
            int max = TextBasedUI.displayMerchant(this);
            int choose = TextBasedUI.getInput(-1, max);
            if (choose == -1) done = true;
            else if (player.getGold() >= prices.get(choose)) {
                    player.masterDeck.addCard(cards.get(choose));
                    cards.remove(choose);
                    prices.remove(choose);
            } else {
                System.out.println("You don't have enough gold to purchase this item.");
            }
        }
    }

    @Override
    public RoomType getType() {
        return type;
    }

    @Override
    public AbstractRoom getChildren() {
        return children;
    }

    @Override
    public boolean getDone() {
        return done;
    }

    public ArrayList<AbstractCard> getCards() {
        return cards;
    }

    public ArrayList<Integer> getPrices() {
        return prices;
    }
}
