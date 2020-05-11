package Models.Cards.Red;

import Controller.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;
import View.Controller;

public class BodySlam extends AbstractCard {

    public BodySlam() {
        name = "BodySlam";
        description = "Deal damage equal to your block.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;
        if (!player.changeEnergy(-cost)) return false;

        AbstractMonster monster = Controller.getMonsterInput();
        FightActions.attack(player, monster, player.getBlock());

        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            this.cost = 0;
            this.upgradable = false;
            this.name = this.name + "+";

        }
    }

    @Override
    public AbstractCard makeCopy() {
        BodySlam copy = new BodySlam();
        copy.upgradable = this.upgradable;
        copy.cost = this.cost;
        return copy;
    }
}