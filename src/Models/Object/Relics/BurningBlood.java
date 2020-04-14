package Models.Object.Relics;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class BurningBlood extends AbstractRelic {

    public BurningBlood(){
        name = "Burning Blood";
        description = "At the end of combat, heal 6 HP.";
        rarity = RelicRarity.STARTER;
        rClass = RelicClass.IRONCLAD;
        amount = 6;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new BurningBlood();
    }

    public void onFightEnd(AbstractCharacter c) {
        c.changeHealth(amount);
    }
}
