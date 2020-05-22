package Models.Object.Potions;

import Controller.Dungeon.Room.Fight;
import Controller.Actions.PowerActions;
import Models.Creatures.AbstractCharacter;
import Models.Object.AbstractPotion;
import Models.Object.PotionType;
import Models.Object.Powers.Agility;

public class AgilityPotion extends AbstractPotion {
    public AgilityPotion() {
        name = "Agility";
        description = "Gain 2 Agility.";
        type = PotionType.DIRECT;
    }

    @Override
    public void use(Fight f, AbstractCharacter player) {
        super.use(f, player);

        Agility ag = new Agility();
        ag.stack(1);

        PowerActions.addPower(player, ag);
    }
}
