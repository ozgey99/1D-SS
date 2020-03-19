package Models.Cards.Red;

import Models.Cards.*;

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
        name = "IronWave";
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
    public void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero) {
        for (AbstractMonster m : monsters) {
            m.changeHealth(-baseAttr.damage);
        }
        hero.changeShield(baseAttr.block);
        hero.currentEnergy -= cost;
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
