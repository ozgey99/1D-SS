package Models.Actions;

import Models.Creatures.AbstractCreature;

public class AttackActions {
    public static void Attack(AbstractCreature to, int amount) {
        to.changeBlock(-amount);
        if (to.getBlock() < 0) {
            to.changeHealth(to.getBlock());
            to.changeBlock(-to.getBlock());
        }
    }
}
