package Models.Cards.Red;

import Controller.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;

public class Cleave extends AbstractCard{

    public Cleave(){
        name = "Cleave";
        description = "Deal 8(11) damage to ALL enemies.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ALL_ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 8;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;
        if (!player.changeEnergy(-cost)) return false;
        for (AbstractMonster m : f.getMonsters()) {
            FightActions.attack(player, m, baseAttr.damage);
        }
        return true;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
            baseAttr.damage = 11;
            this.name = this.name + "+";

        }
    }

    @Override
    public Cleave makeCopy() {
        Cleave newCard = new Cleave();
        newCard.upgradable = this.upgradable;
        newCard.baseAttr.damage = this.baseAttr.damage;
        return newCard;
    }
}