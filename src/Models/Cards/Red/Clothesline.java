package Models.Cards.Red;

import Models.Cards.*;

import java.util.ArrayList;

public class Clothesline extends AbstractCard {
    private String name;
    private String description;
    private int cost;
    private CardType type;
    private CardColor color;
    private CardRarity rarity;
    private CardTarget target;
    private CardKeyword keyword;
    private BaseCardAttributes baseAttr;
    private boolean usable;
    private boolean upgradable;

    private Clothesline(){
        name = "Clothesline";
        description = "Deal 12(14) damage. Apply 2(3) Weak.";
        cost = 2;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 12;
        usable = true;
        upgradable = true;
    }

    @Override
    public void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero) {
        //monster's health goes down by "damage" amount
        for (AbstractMonster m : monsters) {
            m.changeHealth(-baseAttr.damage);
        }
        //apply "weak" amount to monster
        Weak w = new Weak();
        if(upgradable)
            w.stackPower(2);
        else
            w.stackPower(3);
        monster.addPower(w);
        //subtract cost from the energy
        hero.currentEnergy -= cost;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
            baseAttr.damage = 14;
        }
    }

    @Override
    public Clothesline makeCopy() {
        Clothesline newCard = new Clothesline();
        newCard.upgradable = this.upgradable;
        newCard.baseAttr.damage = this.baseAttr.damage;
        return newCard;
    }
}
