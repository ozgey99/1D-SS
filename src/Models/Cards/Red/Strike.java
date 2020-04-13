package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

public class Strike extends AbstractCard {

    public Strike() {
        name = "Strike";
        description = "Deal 5 damage.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.BASIC;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 5;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;

        System.out.print("Choose enemy to attack: ");
        int monster = UI.getInput(0, f.getMonsters().size());
        FightActions.attack(player, f.getMonsters().get(monster), baseAttr.damage);

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
        Strike copy = new Strike();
        copy.upgradable = this.upgradable;
        copy.baseAttr.damage = this.baseAttr.damage;
        return copy;
    }
}
