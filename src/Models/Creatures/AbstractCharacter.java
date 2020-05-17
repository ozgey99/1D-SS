package Models.Creatures;

import Models.Cards.Deck;
import Models.Object.AbstractRelic;

import java.util.ArrayList;

public abstract class AbstractCharacter extends AbstractCreature {
    public int currentEnergy;
    public int maxEnergy;
    public Pet pet;

    protected int gold;
    protected boolean isAlive;

    public Deck masterDeck = new Deck();
    public ArrayList<AbstractRelic> relics = new ArrayList<>();

    public boolean changeEnergy(int amount) {
        if (currentEnergy + amount < 0) return false;
        currentEnergy += amount;
        return true;
    }

    public void changeMaxHP(int hp){
        maxHP += hp;
    }
    public void changeGold(int amount) { gold += amount; }

    public void recharge()
    {
        currentHP = currentHP + (int) ((maxHP * 3) / 10);
        if(currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

    public int getGold() {
        return gold;
    }

    public Pet getPet(){
        return pet;
    }

    public boolean buyPet(Pet p){
        if(pet == null) {
            pet = p;
            return true;
        }
        return false;
    }
}