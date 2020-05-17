package Models.Actions;

import Models.Creatures.AbstractCreature;
import Models.Object.AbstractPower;
import Models.Utils;

import java.util.ArrayList;

public class PowerActions {
    public static void addPower(AbstractCreature to, AbstractPower power) {
        AbstractPower p = Utils.getInstance(to.powers, power.getClass());
        if (p == null) {
            to.powers.add(power);
        } else {
            p.stack(power.getAmount());
        }
    }

    public static void turnEndDecrease(AbstractCreature c) {
        ArrayList<AbstractPower> toRemove = new ArrayList<>();

        for (AbstractPower p : c.powers) {
            if (p.doesDecreaseEachTurn) {
                p.stack(-1);
                if (p.getAmount() == 0) {
                    toRemove.add(p);
                }
            }
        }

        for (AbstractPower p : toRemove) {
            c.powers.remove(p);
        }
    }
}
