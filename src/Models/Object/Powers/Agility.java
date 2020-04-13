package Models.Object.Powers;

import Models.Object.AbstractPower;

public class Agility extends AbstractPower {
    public Agility() {
        name = "Agility";
        description = "Increases the block gained by 1.";
        amount = 1;
        doesDecreaseEachTurn = false;
    }

    @Override
    public AbstractPower makeCopy() {
        Agility copy = new Agility();
        copy.amount = this.amount;
        return copy;
    }

    @Override
    public int onAttack(int prevDamage) {
        return prevDamage;
    }

    @Override
    public int onDamage(int prevDamage) {
        return prevDamage;
    }

    @Override
    public int onBlock(int prevBlock) {
        return prevBlock + 1;
    }
}
