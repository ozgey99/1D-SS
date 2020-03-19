package Models.Cards.Red;

import Models.Cards.*;

import java.util.ArrayList;

public class Defend extends AbstractCard {
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

    public Defend() {
        name = "Defend";
        description = "Gain 5 Block.";
        cost = 1;
        type = CardType.SKILL;
        color = CardColor.RED;
        rarity = CardRarity.BASIC;
        target = CardTarget.SELF;
        baseAttr = new BaseCardAttributes();
        baseAttr.block = 5;
        usable = true;
        upgradable = true;
    }

    @Override
    public void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero) {
        hero.changeShield(baseAttr.block);
        hero.currentEnergy -= cost;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            this.baseAttr.block = 8;
            upgradable = false;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        Defend copy = new Defend();
        copy.upgradable = this.upgradable;
        copy.baseAttr.block = this.baseAttr.block;
        return copy;
    }
}
