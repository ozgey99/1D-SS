package Models.Creatures.Characters;

import Models.Cards.Red.Defend;
import Models.Cards.Red.Strike;
import Models.Creatures.AbstractCharacter;
import Models.Object.Relics.BurningBlood;

public class Ironclad extends AbstractCharacter {

    //constructor
    public Ironclad() {
        maxHP = 100;
        currentHP = maxHP;
        maxEnergy = 3;
        gold = 100;

        isAlive = true;
    }

    @Override
    public void initialize() {
        for (int i = 0; i < 5; i++) {
            masterDeck.addCard(new Strike());
            masterDeck.addCard(new Defend());
        }
        relics.add(new BurningBlood());
    }

    public int getHealth()
    {
        return currentHP;
    }

    public int getGold() {
        return gold;
    }

//    @Override
//    public void changeGold(int change) {
//        gold += change;
//    }

    @Override
    public void recharge() {
        currentEnergy = maxEnergy;
    }
}