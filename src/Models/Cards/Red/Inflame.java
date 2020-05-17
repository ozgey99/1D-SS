package Models.Cards.Red;

import Models.Actions.PowerActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Controller.Dungeon.Room.Fight;
import Models.Object.Powers.Strength;

public class Inflame extends AbstractCard {
    public Inflame(){
        name = "Inflame";
        description = "Gain 2(3) Strength.";
        cost = 1;
        type = CardType.POWER;
        color = CardColor.RED;
        rarity = CardRarity.UNCOMMON;
        target = CardTarget.SELF;
        baseAttr = new BaseCardAttributes();
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {

        selected = false;
        if (!player.changeEnergy(-cost)) return false;

        Strength s = new Strength();

        if(upgradable)
            s.stack(1);
        else
            s.stack(2);

        PowerActions.addPower(player, s);

        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.name = this.name + "+";

        }
    }

    @Override
    public AbstractCard makeCopy() {
        Inflame copy = new Inflame();
        copy.upgradable = this.upgradable;
        return copy;
    }
}
