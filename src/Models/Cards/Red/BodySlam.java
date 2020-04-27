package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;
import Models.TextBasedUI;
import sts.Controller;

public class BodySlam extends AbstractCard {

    public BodySlam() {
        name = "Body Slam";
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