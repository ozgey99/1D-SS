package Models.Cards.Red;

import Controller.Actions.FightActions;
import Controller.Actions.PowerActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;
import Models.Object.Powers.Vulnerable;
import View.Controller;

public class Bash extends AbstractCard {
    public Bash(){
        name = "Bash";
        description = "Deal 8(10) damage. Apply 2(3) Vulnerable.";
        cost = 2;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.BASIC;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 8;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;

        if (!player.changeEnergy(-cost)) return false;

        AbstractMonster monster = Controller.getMonsterInput();
        FightActions.attack(player, monster, baseAttr.damage);

        Vulnerable v = new Vulnerable();

        if(upgradable)
            v.stack(1);
        else
            v.stack(2);

        PowerActions.addPower(monster, v);

        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.baseAttr.damage = 10;
            this.name = this.name + "+";

        }
    }

    @Override
    public AbstractCard makeCopy() {
        Bash copy = new Bash();
        copy.upgradable = this.upgradable;
        copy.baseAttr.damage = this.baseAttr.damage;
        return copy;
    }
}
