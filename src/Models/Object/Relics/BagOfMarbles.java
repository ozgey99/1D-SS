package Models.Object.Relics;

import Controller.Actions.PowerActions;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.Powers.Vulnerable;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class BagOfMarbles extends AbstractRelic {

    public BagOfMarbles(){
        name = "BagOfMarbles";
        description = "At the start of each combat, apply 1 Vulnerable to ALL enemies.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new BagOfMarbles();
    }

    @Override
    public void onFightStart(Fight f, AbstractCharacter c) {
        for(AbstractMonster m : f.getMonsters()){
            PowerActions.addPower(m, new Vulnerable());
        }

    }
}
