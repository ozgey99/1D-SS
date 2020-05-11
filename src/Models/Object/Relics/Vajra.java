package Models.Object.Relics;

import Controller.Actions.PowerActions;
import Models.Creatures.AbstractCharacter;
import Controller.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.Powers.Strength;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class Vajra extends AbstractRelic {

    public Vajra(){
        name = "Vajra";
        description = "At the start of each combat, gain 1 Strength.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new Vajra();
    }

    @Override
    public void onFightStart(Fight f, AbstractCharacter c) {
        PowerActions.addPower(c, new Strength());
    }

}
