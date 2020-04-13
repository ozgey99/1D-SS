package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

public class TwinStrike extends AbstractCard {

    private int repeat;

    public TwinStrike(){
        name = "Twin Strike";
        description = "Deal 5(7) damage twice.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 5;
        usable = true;
        upgradable = true;
        repeat = 2;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;
        int monster = UI.getInput(0, f.getMonsters().size());
        for(int i = 0; i < repeat; i++)
            FightActions.attack(player, f.getMonsters().get(monster), baseAttr.damage);
        return true;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
            baseAttr.damage = 7;
        }
    }

    @Override
    public TwinStrike makeCopy() {
        TwinStrike newCard = new TwinStrike();
        newCard.upgradable = this.upgradable;
        newCard.baseAttr.damage = this.baseAttr.damage;
        return newCard;
    }
}