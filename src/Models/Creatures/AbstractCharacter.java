package Models.Creatures;

import Models.Cards.Deck;
import Models.Object.AbstractRelic;

import java.util.ArrayList;

public abstract class AbstractCharacter extends AbstractCreature {
    public int currentEnergy;
    public int maxEnergy;

    protected int gold;
    protected boolean isAlive;

    public Deck masterDeck = new Deck();
    public ArrayList<AbstractRelic> relics = new ArrayList<>();
//    public Deck hand = new Deck();
//    public Deck discard = new Deck();
//    public Deck exhaust = new Deck();
//    public Deck draw = new Deck();

    //ArrayList<Potion> potions = new ArrayList<Potion>();
    //ArrayList<Relic> relics = new ArrayList<Relic>();

    public boolean changeEnergy(int usage) {
        if (currentEnergy + usage < 0) return false;
        currentEnergy += usage;
        return true;
    }

    public void changeMaxHP(int hp){
        maxHP += hp;
    }

    void changeGold(int change)
    {
        gold = gold + change;
    }
    /*
    void initialize()
    {
       deck.addCard(InıtialCard);
       deck.addCard(InıtialCard2);
       potions.addPotion(InitialPotion);
       relics.addRelic(InitialRelic);
       gold = 100;
       currentHp = 80;
       maxHp = 100;

    }
    void addRelic(Relic relic)
    {
        relics.add(relic);
    }
    void removeRelic(Relic relic)
    {
        relics.remove(relic);
    }
       void addPotion(Potion potion)
    {
        potions.add(potion);
    }
    void removePotion(Potion potion)
    {
        potions.remove(potion);
    }
     */
    public void recharge()
    {
        currentHP = currentHP + (int) ((maxHP * 3) / 10);
        if(currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

}