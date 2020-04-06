package Models.Cards.Red;

import Models.Actions.AttackActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

public class SearingBlow extends AbstractCard {

    private int n;

    public SearingBlow(){
        n = 0;
        name = "Searing Blow";
        description = "Deal 12(+) damage. Can be upgraded any number of times.";
        cost = 2;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.UNCOMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 12;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;
        int monster = UI.getInput(0, f.getMonsters().size());
        AttackActions.Attack(f.getMonsters().get(monster), baseAttr.damage);
        return true;
    }

    @Override
    public void upgrade() {
        n++;
        this.baseAttr.damage = n *(n + 7) / 2 + 12;
    }

    @Override
    public AbstractCard makeCopy() {
        SearingBlow copy = new SearingBlow();
        copy.n = this.n;
        copy.baseAttr.damage = this.baseAttr.damage;
        return copy;
    }

}
