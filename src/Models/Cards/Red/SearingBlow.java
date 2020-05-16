package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;
import View.Controller;

public class SearingBlow extends AbstractCard {

    private int n;

    public SearingBlow(){
        n = 0;
        name = "SearingBlow";
        description = "Deal 12(+) damage. Can be upgraded any number of times.";
        cost = 2;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.UNCOMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 12;
        usable = true;
        upgradable = false;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;
        if (!player.changeEnergy(-cost)) return false;
        AbstractMonster monster = Controller.getMonsterInput();
        FightActions.attack(player, monster, baseAttr.damage);
        return true;
    }

    @Override
    public void upgrade() {
        if(upgradable) {
            n++;
            this.baseAttr.damage = n * (n + 7) / 2 + 12;
            this.name = this.name + "+";
            upgradable = false;
        }

    }

    @Override
    public AbstractCard makeCopy() {
        SearingBlow copy = new SearingBlow();
        copy.n = this.n;
        copy.baseAttr.damage = this.baseAttr.damage;
        return copy;
    }

}
