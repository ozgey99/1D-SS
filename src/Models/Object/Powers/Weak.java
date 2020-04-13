package Models.Object.Powers;

import Models.Object.AbstractPower;

public class Weak extends AbstractPower {
    public Weak() {
        name = "Weak";
        description = "Target deals 25% less attack damage.";
        amount = 1;
        doesDecreaseEachTurn = true;
    }

    @Override
    public AbstractPower makeCopy() {
        Weak copy = new Weak();
        copy.amount = this.amount;
        return copy;
    }

    @Override
    public int onAttack(int prevDamage) {
        return prevDamage * 3 / 4;
    }

    @Override
    public int onDamage(int prevDamage) {
        return prevDamage;
    }

    @Override
    public int onBlock(int prevBlock) {
        return prevBlock;
    }
}
