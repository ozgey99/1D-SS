package Models.Object;

import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import javafx.scene.image.Image;

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

    public String getDescription() {
        return description;
    }

    public abstract void affect(Fight f, AbstractCharacter player);
}
