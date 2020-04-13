package Models.Object.Powers;

import Models.Actions.PowerActions;
import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;
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
    public int onAttack(int prevDamage) {
        return prevDamage;
    }

    @Override
    public int onDamage(int prevDamage) {
        return prevDamage;
    }

    @Override
    public void onDamage(AbstractCreature c) {
        c.changeBlock(amount);
        amount = 0;
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
