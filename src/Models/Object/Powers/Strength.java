package Models.Object.Powers;

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
}
