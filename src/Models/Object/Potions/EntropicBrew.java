package Models.Object.Potions;

import Controller.Actions.PotionActions;
import Controller.Dungeon.Room.Fight;
import Models.Creatures.AbstractCharacter;
import Models.Object.AbstractPotion;
import Models.Object.PotionType;
import Models.Utils;

import java.util.ArrayList;

public class EntropicBrew extends AbstractPotion {
    public EntropicBrew() {
        name = "Entropic Brew";
        description = "Fill all your empty potion slots with random potions.";
        type = PotionType.DIRECT;
    }

    @Override
    public void use(Fight f, AbstractCharacter player) {
        super.use(f, player);

        ArrayList<AbstractPotion> potions = Utils.getAllPotions();
        AbstractPotion p;
        do {
            p = potions.get(Utils.random(potions.size() - 1, 0));
        } while (PotionActions.addPotion(player, p));
    }
}
