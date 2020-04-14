package Models.Object.Powers;

import Models.Creatures.AbstractCreature;
import Models.Object.AbstractPower;

public class CurlUp extends AbstractPower {
    public CurlUp() {
        name = "Curl Up";
        amount = (int) (Math.random() * 4 + 3);
        description = "Gain " + amount + " block on damage received.";
        doesDecreaseEachTurn = false;
    }

    @Override
    public AbstractPower makeCopy() {
        CurlUp copy = new CurlUp();
        copy.amount = this.amount;
        return copy;
    }

    @Override
    public void onDamage(AbstractCreature c) {
        c.changeBlock(amount);
        amount = 0;
    }
}
