package Models.Cards.Red;

import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Controller.Dungeon.Room.Fight;

public class Havoc extends AbstractCard {

    public Havoc(){
        name = "Havoc";
        description = "Play the top card of your draw pile and Exhaust it.";
        cost = 1;
        type = CardType.SKILL;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.NONE;
        baseAttr = new BaseCardAttributes();
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;
        if (!player.changeEnergy(-cost)) return false;
        AbstractCard c = f.getDraw().getCard(0);
        c.use(f, player);
        f.getDraw().removeCard(c);
        f.getExhaust().addCard(c);
        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.cost = 0;
            this.name = this.name + "+";

        }
    }

    @Override
    public AbstractCard makeCopy() {
        Havoc copy = new Havoc();
        copy.upgradable = this.upgradable;
        copy.cost = this.cost;
        return copy;
    }
}
