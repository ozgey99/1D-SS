package Models.Object.Relics;

import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class Lantern extends AbstractRelic {

    public Lantern(){
        name = "Lantern";
        description = "Gain 1 Energy on the first turn of each combat.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
        amount = 1;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new Lantern();
    }

    @Override
    public void onFightStart(Fight f, AbstractCharacter c) {
        c.changeEnergy(amount);
    }
}
