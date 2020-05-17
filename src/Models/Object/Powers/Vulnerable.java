package Models.Object.Powers;

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
    public int onDamage(int prevDamage) {
        return prevDamage + (prevDamage / 4);
    }
}
