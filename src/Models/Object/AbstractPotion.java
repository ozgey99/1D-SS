package Models.Object;

import Controller.Dungeon.Room.Fight;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;

import java.io.Serializable;

public abstract class AbstractPotion implements Serializable {
    protected String name;
    protected String description;
    protected PotionType type;

    public void use(Fight f, AbstractCharacter player) {
        player.potions.remove(this);
    }

    public void use(AbstractCreature c,  AbstractCharacter player) {
        player.potions.remove(this);
    }

    public void use(AbstractCharacter player) {
        player.potions.remove(this);
    }
}
