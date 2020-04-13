package Models.Object;

import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;

public abstract class AbstractObject {
    public abstract int onAttack(int prevDamage);
    public abstract int onDamage(int prevDamage);
    public abstract void onDamage(AbstractCreature c);
    public abstract int onBlock(int prevBlock);
    public abstract void onTurnStart(Fight f);
    public abstract void onTurnStart(AbstractCreature c);
}
