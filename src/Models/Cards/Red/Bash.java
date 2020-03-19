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

    public Bash(){
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
        Vulnerable v = new Vulnerable();
        if(upgradable)
            v.stackPower(2);
        else
            v.stackPower(3);
        for (AbstractMonster m : monsters) {
            m.changeHealth(-baseAttr.damage);
            m.addPower(v);
        }
        //subtract cost from the energy
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
        newCard.baseAttr.damage = this.baseAttr.damage;
        return newCard;
    }
}
