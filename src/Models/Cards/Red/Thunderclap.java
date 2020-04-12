package Models.Cards.Red;

import Models.Actions.AttackActions;
import Models.Actions.PowerActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;
import Models.Object.Powers.Vulnerable;
import Models.UI;

public class Thunderclap extends AbstractCard {
    public Thunderclap(){
        name = "Thunderclap";
        description = "Deal 4(7) damage and apply 1 Vulnerable to ALL enemies.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ALL_ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 4;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;

        Vulnerable v = new Vulnerable();

        for(AbstractMonster m : f.getMonsters()) {
            AttackActions.Attack(player, m, baseAttr.damage);
            PowerActions.addPower(m, v);
        }

        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.baseAttr.damage = 7;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        Thunderclap copy = new Thunderclap();
        copy.upgradable = this.upgradable;
        copy.baseAttr.damage = this.baseAttr.damage;
        return copy;
    }
}
