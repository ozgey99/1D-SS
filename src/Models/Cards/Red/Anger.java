package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

public class Anger extends AbstractCard {

    public Anger() {
        name = "Anger";
        description = "Deal 6 damage. Add a copy of this card into your discard pile.";
        cost = 0;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 6;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        int monster = UI.getInput(0, f.getMonsters().size());
        FightActions.attack(player, f.getMonsters().get(monster), baseAttr.damage);
        Deck discardPile = f.getDiscard();
        discardPile.addCard((this.makeCopy()));
        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            this.baseAttr.damage = 8;
            upgradable = false;
        }
    }

    @Override
    public Anger makeCopy() {
        Anger copy = new Anger();
        copy.upgradable = upgradable;
        copy.baseAttr.damage = this.baseAttr.damage;
        return  copy;
    }
}