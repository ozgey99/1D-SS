package Models.Cards.Red;

import Models.Cards.*;

import java.util.ArrayList;

public class Bash extends AbstractCard {

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

    private Bash(){
        name = "Bash";
        description = "Deal 8(10) damage. Apply 2(3) Vulnerable.";
        cost = 2;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.BASIC;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 8;
        usable = true;
        upgradable = true;
    }

    @Override
    public void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero) {
        //monster's health goes down by "damage" amount
        for (AbstractMonster m : monsters) {
            m.changeHealth(-baseAttr.damage)
        }
        //apply "vulnerable" amount to monster
        Vulnerable v = new Vulnerable();
        if(upgradable)
            v.stackPower(2);
        else
            v.stackPower(3);
        monster.addPower(v);
        //subtract 2 from the energy
        hero.currentEnergy -= cost;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
            baseAttr.damage = 10;
        }
    }

    @Override
    public Bash makeCopy() {
        Bash newCard = new Bash();
        newCard.upgradable = this.upgradable;
        newCard.baseAttr = this.baseAttr;
        return newCard;
    }
}
