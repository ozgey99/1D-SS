package Models.Cards.Red;

import Controller.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;
import View.Controller;

public class Headbutt extends AbstractCard {

    public Headbutt(){
        name = "Headbutt";
        description = "Deal 9(12) damage. Place a card from your discard pile on top of your draw pile.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 9;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;
        if (!player.changeEnergy(-cost)) return false;
        AbstractMonster monster = Controller.getMonsterInput();
        FightActions.attack(player, monster, baseAttr.damage);

        AbstractCard c = f.getDiscard().getCard((int)(Math.random() * f.getDiscard().getSize()));
        f.getDiscard().removeCard(c);
        f.getDraw().getCardList().add(0, c);
        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.baseAttr.damage = 12;
            this.name = this.name + "+";

        }
    }

    @Override
    public AbstractCard makeCopy() {
        Headbutt copy = new Headbutt();
        copy.upgradable = this.upgradable;
        copy.baseAttr.damage = this.baseAttr.damage;
        return copy;
    }

}
