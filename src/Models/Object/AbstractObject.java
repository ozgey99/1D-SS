package Models.Object;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;

public abstract class AbstractObject {
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
