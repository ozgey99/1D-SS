package Models.Object.Relics;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class Anchor extends AbstractRelic {

    public Anchor(){
        name = "Anchor";
        description = "Start each combat with 10 Block.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
        amount = 10;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new Anchor();
    }

    @Override
    public void onFightStart(Fight f, AbstractCharacter c) {
        c.changeBlock(amount);
    }
}
