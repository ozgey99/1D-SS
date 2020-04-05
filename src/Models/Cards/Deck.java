package Models.Cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public static Deck drawCard(Deck draw, Deck discard, int amount) {
        Deck drawn = new Deck();

        while (amount > 0) {
            if (draw.isEmpty()) {
                discard.shuffle();
                while (!discard.isEmpty()) draw.addCard(discard.getAndRemoveFirst());
            }

            drawn.addCard(draw.getAndRemoveFirst());
            amount--;
        }

        return drawn;
    }

    private ArrayList<AbstractCard> cardList;
    public DeckType type;

    public Deck() {
        cardList = new ArrayList<AbstractCard>();
    }

    public ArrayList<AbstractCard> getCardList(){ return cardList; }

    public boolean addCard(AbstractCard c) {
        return cardList.add(c);
    }

    public void addDeck(Deck d) {
        while (!d.isEmpty()) {
            addCard(d.getAndRemoveFirst());
        }
    }

    public boolean removeCard(AbstractCard c) {
        return cardList.remove(c);
    }

    public void removeAll() {
        cardList.clear();
    }

    public boolean isEmpty() {
        return cardList.isEmpty();
    }

    public void shuffle() {
        Collections.shuffle(cardList);
    }

    public void copyCards(Deck d) {
        for (int i = 0; i < d.getSize(); i++) {
            addCard(d.getCard(i));
        }
    }

    public AbstractCard getAndRemoveFirst() {
        if (cardList.isEmpty()) return null;
        AbstractCard c = cardList.get(0);
        cardList.remove(0);
        return c;
    }

    public AbstractCard getCard(int index) {
        if (index >= getSize()) return null;
        return cardList.get(index);
    }

    public int getSize() {
        return cardList.size();
    }


    public AbstractCard removeCard(int card) {
        AbstractCard c = cardList.get(card);
        cardList.remove(card);
        return c;
    }
}
