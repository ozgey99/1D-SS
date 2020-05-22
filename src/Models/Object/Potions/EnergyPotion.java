package Models.Object.Potions;

import Controller.Actions.FightActions;
import Controller.Dungeon.Room.Fight;
import Models.Creatures.AbstractCharacter;
import Models.Object.AbstractPotion;
import Models.Object.PotionType;

public class EnergyPotion extends AbstractPotion {
    public EnergyPotion() {
        name = "Energy Potion";
        description = "Gain 2 Energy.";
        type = PotionType.DRAW;
    }

    @Override
    public void use(AbstractCharacter player) {
        super.use(player);

        player.potions.remove(this);
        player.changeEnergy(2);
    }
}
