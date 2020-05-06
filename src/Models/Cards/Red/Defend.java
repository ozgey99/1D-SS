package Models.Cards.Red;

import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Controller.Dungeon.Room.Fight;

public class Defend extends AbstractCard {

    public Defend() {
        name = "Defend";
        description = "Gain 5 block.";
        cost = 1;
        type = CardType.SKILL;
        color = CardColor.RED;
        rarity = CardRarity.BASIC;
        target = CardTarget.SELF;
        baseAttr = new BaseCardAttributes();
        baseAttr.block = 5;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;
        if (!player.changeEnergy(-cost)) return false;
        player.changeBlock(baseAttr.block);
        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            this.baseAttr.block = 8;
            upgradable = false;
            this.name = this.name + "+";

        }
    }

    @Override
    public AbstractCard makeCopy() {
        Defend copy = new Defend();
        copy.upgradable = this.upgradable;
        copy.baseAttr.block = this.baseAttr.block;
        return copy;
    }
}
