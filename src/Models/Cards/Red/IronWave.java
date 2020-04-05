package Models.Cards.Red;

import Models.Actions.AttackActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

import java.util.ArrayList;

public class IronWave extends AbstractCard {
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

    public IronWave(){
        name = "Iron Wave";
        description = "Gain 5(7) Block. Deal 5(7) damage.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 5;
        baseAttr.block = 5;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;
        int monster = UI.getInput(0, f.getMonsters().size());
        AttackActions.Attack(f.getMonsters().get(monster), baseAttr.damage);
        player.changeBlock(baseAttr.block);
        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            baseAttr.block = 7;
            baseAttr.damage = 7;
            upgradable = false;
        }
    }

    @Override
    public IronWave makeCopy() {
        IronWave newCard = new IronWave();
        newCard.upgradable = this.upgradable;
        newCard.baseAttr.damage = this.baseAttr.damage;
        newCard.baseAttr.block = this.baseAttr.block;
        return newCard;
    }
}