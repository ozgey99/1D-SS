package Models.Cards.Red;

import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;

public class Entrench extends AbstractCard {

    public Entrench(){
        name = "Entrench";
        description = "Double your current Block.";
        cost = 2;
        type = CardType.SKILL;
        color = CardColor.RED;
        rarity = CardRarity.UNCOMMON;
        target = CardTarget.SELF;
        baseAttr = new BaseCardAttributes();
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;
        player.changeBlock(player.getBlock());
        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            cost = 1;
            upgradable = false;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        Entrench copy = new Entrench();
        copy.upgradable = this.upgradable;
        copy.cost = this.cost;
        return copy;
    }
}
