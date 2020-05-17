package Controller.Actions;

import Models.Creatures.AbstractCharacter;
import Models.Object.AbstractPotion;

public class PotionActions {
    public static boolean addPotion(AbstractCharacter to, AbstractPotion potion) {
        if (to.potions.size() == to.maxPotionAmount) return false;
        to.potions.add(potion);
        return true;
    }
}
