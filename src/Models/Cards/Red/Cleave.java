package Models.Cards.Red;

import Models.Cards.*;

import java.util.ArrayList;

public class Cleave extends AbstractCard{
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

    public Cleave(){
        name = "Cleave";
        description = "Deal 8(11) damage to ALL enemies.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ALL_ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 8;
        usable = true;
        upgradable = true;
    }

    @Override
    public void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero) {
        //monsters' health goes down by "damage" amount
        for (AbstractMonster m : monsters) {
            m.changeHealth(-baseAttr.damage);
        }
        //subtract cost from the energy
        hero.currentEnergy -= cost;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
            baseAttr.damage = 11;
        }
    }

    @Override
    public Cleave makeCopy() {
        Cleave newCard = new Cleave();
        newCard.upgradable = this.upgradable;
        newCard.baseAttr.damage = this.baseAttr.damage;
        return newCard;
    }
}
