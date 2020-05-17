package Models.Object.Potions;

import Controller.Dungeon.Room.Fight;
import Models.Cards.Red.Strike;
import Models.Creatures.AbstractCharacter;
import Models.Object.AbstractPotion;
import Models.Object.PotionType;

public class StrikePotion extends AbstractPotion {
    public StrikePotion() {
        name = "Strike Potion";
        description = "Add one Strike to your hand.";
        type = PotionType.DIRECT;
    }

    @Override
    public void use(Fight f, AbstractCharacter player) {
        super.use(f, player);

        f.getHand().addCard(new Strike());
        f.getHand().addCard(new Strike());
    }
}
