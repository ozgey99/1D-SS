package Models.Object;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Controller.Dungeon.Room.Fight;

import java.io.Serializable;

public abstract class AbstractObject implements Serializable {
    public int onAttack(int prevDamage) {
        return prevDamage;
    }
    public int onDamage(int prevDamage) {
        return prevDamage;
    }
    public void onDamage(AbstractCreature c) {}
    public int onBlock(int prevBlock) {
        return prevBlock;
    }
    public void onTurnStart(Fight f) {}
    public void onTurnStart(AbstractCreature c) {}
    public void onFightStart(Fight f, AbstractCharacter c) {}
    public void onTurnEnd(AbstractCreature c) {}
    public void onFightEnd(AbstractCharacter c) {}
}
