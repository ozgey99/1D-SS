package Models.Cards.Colorless;

import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Controller.Dungeon.Room.Fight;

public class Dazed extends AbstractCard {
    public Dazed() {
        name = "Dazed";
        description = "Unplayable. Ethereal.";
        cost = 0;
        type = CardType.STATUS;
        keyword = CardKeyword.ETHEREAL;
        color = CardColor.COLORLESS;
        rarity = CardRarity.COMMON;
        target = CardTarget.NONE;
        usable = false;
        upgradable = false;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        return false;
    }

    @Override
    public void upgrade() {
        return;
    }

    @Override
    public Dazed makeCopy() {
        return new Dazed();
    }
}
