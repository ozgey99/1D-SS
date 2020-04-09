package Models.Cards.Red;

import Models.Actions.AttackActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

public class Hemokinesis extends AbstractCard {

    private int HPdecrease;

    public Hemokinesis(){
        name = "Hemokinesis";
        description = "Lose 3(2) HP. Deal 14(18) damage.";
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.UNCOMMON;
        target = CardTarget.ENEMY;
        usable = true;
        upgradable = true;
        HPdecrease = 3;
        baseAttr.damage = 14;
        cost = 1;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if(player.getCurrentHP() > HPdecrease) {
            if (!player.changeEnergy(-cost)) return false;

            player.changeHealth(-HPdecrease);
            int monster = UI.getInput(0, f.getMonsters().size());
            AttackActions.Attack(player, f.getMonsters().get(monster), baseAttr.damage);

            return true;
        }
        return false;
    }

    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.baseAttr.damage = 18;
            HPdecrease = 2;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        Hemokinesis copy = new Hemokinesis();
        copy.upgradable = this.upgradable;
        copy.baseAttr.damage = this.baseAttr.damage;
        copy.HPdecrease = this.HPdecrease;
        return copy;
    }
}
