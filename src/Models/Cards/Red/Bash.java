package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Actions.PowerActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.Object.Powers.Vulnerable;
import Models.UI;

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
        if (!player.changeEnergy(-cost)) return false;

        int monster = UI.getInput(0, f.getMonsters().size());
        FightActions.attack(player, f.getMonsters().get(monster), baseAttr.damage);

        Vulnerable v = new Vulnerable();

        if(upgradable)
            v.stack(1);
        else
            v.stack(2);

        PowerActions.addPower(f.getMonsters().get(monster), v);

        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.baseAttr.damage = 10;
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
