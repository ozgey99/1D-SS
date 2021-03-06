package Models.Cards.Red;

import Controller.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;
import View.Controller;

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
        selected = false;
        AbstractMonster monster = Controller.getMonsterInput();
        FightActions.attack(player, monster, baseAttr.damage);
        Deck discardPile = f.getDiscard();
        discardPile.addCard((this.makeCopy()));
        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            this.baseAttr.damage = 8;
            upgradable = false;
            this.name = this.name + "+";

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