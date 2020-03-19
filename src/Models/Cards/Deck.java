package Models.Cards;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<AbstractCard> cardList;
    public DeckType type;

    public Deck(){
        cardList = new ArrayList<AbstractCard>();
    }
    public boolean addCard(AbstractCard c){
        return cardList.add(c);
    }
    public boolean removeCard(AbstractCard c){
        return cardList.remove(c);
    }
    public void removeAll(){
        cardList.clear();
    }
    public boolean isEmpty(){
        return cardList.isEmpty();
    }
    public void shuffle(){
        Collections.shuffle(cardList);
    }
}
