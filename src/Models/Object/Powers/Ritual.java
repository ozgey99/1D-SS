package Models.Object.Powers;

import Models.Actions.PowerActions;
import Models.Creatures.AbstractCreature;
import Models.Object.AbstractPower;

public class Ritual extends AbstractPower {

    public Ritual() {
        name = "Ritual";
        description = "At the end of its turn gain 1 strength.";
        amount = 1;
        doesDecreaseEachTurn = false;
    }


    @Override
    public void stack(int a) {
        super.stack(a);
        description = "At the end of its turn gain "+ amount + " strength.";
    }

    @Override
    public AbstractPower makeCopy() {
        Ritual copy = new Ritual();
        copy.amount = this.amount;
        return copy;
    }

    @Override
    public void onTurnEnd(AbstractCreature c) {

        Strength s = new Strength();
        s.stack(amount - 1);

        PowerActions.addPower(c, s);

    }
}
