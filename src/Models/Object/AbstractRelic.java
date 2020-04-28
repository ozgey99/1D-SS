package Models.Object;

import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;

public abstract class AbstractRelic extends AbstractObject {
    protected String name;
    protected String description;
    protected RelicRarity rarity;
    protected RelicClass rClass;
    protected int amount;

    public abstract AbstractRelic makeCopy();

    public String getName() {
        return name;
    }

    public RelicRarity getRarity(){
        return rarity;
    }

    public RelicClass getrClass(){
        return rClass;
    }

    public String getDescription() {
        return description;
    }

}
