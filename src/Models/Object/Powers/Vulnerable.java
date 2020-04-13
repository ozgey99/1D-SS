package Models.Object.Powers;

import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractPower;

public class Vulnerable extends AbstractPower {
    public Vulnerable() {
        name = "Vulnerable";
        description = "Take 25% more damage from attacks.";
        amount = 1;
        doesDecreaseEachTurn = true;
    }

    @Override
    public AbstractPower makeCopy() {
        Vulnerable copy = new Vulnerable();
        copy.amount = this.amount;
        return copy;
    }

    @Override
    public int onAttack(int prevDamage) {
        return prevDamage;
    }

    @Override
    public int onDamage(int prevDamage) {
        return prevDamage + (prevDamage / 4);
    }

    @Override
    public void onDamage(AbstractCreature c) {

    }

    @Override
    public int onBlock(int prevBlock) {
        return prevBlock;
    }

    @Override
    public void onTurnStart(Fight f) {

    }

    @Override
    public void onTurnStart(AbstractCreature c) {

    }
}
