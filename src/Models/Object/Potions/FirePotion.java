package Models.Object.Potions;

import Controller.Actions.FightActions;
import Controller.Dungeon.Room.Fight;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Models.Object.AbstractPotion;
import Models.Object.PotionType;

public class FirePotion extends AbstractPotion {
    public FirePotion() {
        name = "Fire Potion";
        description = "Deal 20 Damage to target enemy.";
        type = PotionType.TARGET;
    }

    @Override
    public void use(AbstractCreature c, AbstractCharacter player) {
        super.use(c, player);

        FightActions.attack(null, c, 20);
    }
}
