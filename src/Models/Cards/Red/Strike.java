package Models.Cards.Red;

import Models.Cards.*;

import java.util.ArrayList;

public class Strike extends AbstractCard {

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
    public void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero) {
        // deal damage
        for (AbstractMonster m : monsters) {
            m.changeHealth(-baseAttr.damage);
        }
        hero.currentEnergy -= cost;
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
