package Models.Cards.Red;

import Models.Actions.AttackActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

public class Bludgeon extends AbstractCard {

    public Bludgeon(){
        name = "Bludgeon";
        description = "Deal 32(42) damage. ";
        cost = 3;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.RARE;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 32;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;

        int monster = UI.getInput(0, f.getMonsters().size());
        AttackActions.Attack(player, f.getMonsters().get(monster), baseAttr.damage);

        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.baseAttr.damage = 42;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        Bludgeon copy = new Bludgeon();
        copy.upgradable = this.upgradable;
        copy.baseAttr.damage = this.baseAttr.damage;
        return copy;
    }
}
