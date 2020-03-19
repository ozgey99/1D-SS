package Models.Cards.Red;

import Models.Cards.*;

import java.util.ArrayList;

public class Thunderclap extends AbstractCard {
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

    public Thunderclap(){
        name = "Thunderclap";
        description = "Deal 4(7) damage and apply 1 Vulnerable to ALL enemies.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ALL_ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 4;
        usable = true;
        upgradable = true;
    }

    @Override
    public void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero) {
        Vulnerable v = new Vulnerable();
        v.stackPower(1);
        for (AbstractMonster m : monsters) {
            m.changeHealth(-baseAttr.damage);
            m.addPower(v);
        }
        hero.currentEnergy -= cost;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
            baseAttr.damage = 7;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        Thunderclap newCard = new Thunderclap();
        newCard.upgradable = this.upgradable;
        newCard.baseAttr.damage = this.baseAttr.damage;
        return newCard;
    }
}
