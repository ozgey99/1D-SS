package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;
import View.Controller;

public class Clash extends AbstractCard {

    public Clash(){
        name = "Clash";
        description = "Can only be played if every card in your hand is an attack. Deal 14(18) damage.";
        cost = 0;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 14;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;

        for(AbstractCard c : f.getHand().getCardList()){
            if(c.getType() != CardType.ATTACK)
                return false;
        }
        if (!player.changeEnergy(-cost)) return false;

        AbstractMonster monster = Controller.getMonsterInput();
        FightActions.attack(player, monster, baseAttr.damage);

        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.baseAttr.damage = 18;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        Clash copy = new Clash();
        copy.upgradable = this.upgradable;
        copy.baseAttr.damage = this.baseAttr.damage;
        return copy;
    }
}
