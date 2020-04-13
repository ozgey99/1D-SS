package Models.Object.Powers;

import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractPower;

public class Strength extends AbstractPower {
    public Strength() {
        name = "Strength";
        description = "Strength increases damage by one.";
        amount = 1;
        doesDecreaseEachTurn = false;
    }

    @Override
    public AbstractPower makeCopy() {
        Strength copy = new Strength();
        copy.amount = this.amount;
        return copy;
    }

    @Override
    public int onAttack(int prevDamage) {
        return prevDamage + amount;
    }

    @Override
    public int onDamage(int prevDamage) {
        return prevDamage;
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
