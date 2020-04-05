package Models.Cards.Red;

import Models.Actions.AttackActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

import java.util.ArrayList;

public class TwinStrike extends AbstractCard {
    private String name;
    private String description;
    private int cost;
    private CardType type;
    private CardColor color;
    private CardRarity rarity;
    private CardTarget target;
    private BaseCardAttributes baseAttr;
    private boolean usable;
    private boolean upgradable;
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
            AttackActions.Attack(f.getMonsters().get(monster), baseAttr.damage);
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