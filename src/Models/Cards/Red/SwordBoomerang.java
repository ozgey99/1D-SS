package Models.Cards.Red;

import Models.Cards.*;

import java.util.ArrayList;
import java.util.Collections;

public class SwordBoomerang extends AbstractCard {
    private String name;
    private String description;
    private int cost;
    private int repeat;
    private CardType type;
    private CardColor color;
    private CardRarity rarity;
    private CardTarget target;
    private BaseCardAttributes baseAttr;
    private boolean usable;
    private boolean upgradable;

    public SwordBoomerang(){
        name = "Sword Boomerang";
        description = "Deal 3 damage to a random enemy 3(4) times.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 3;
        usable = true;
        upgradable = true;
        repeat = 3;
    }

    @Override
    public void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero) {
        ArrayList<AbstractMonster> copy = Collections.shuffle(monsters);
        for(int i = 0; i < repeat; i++) {
            for (AbstractMonster m : copy) {
                m.changeHealth(-baseAttr.damage);
            }
        }
        hero.currentEnergy -= cost;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
            repeat = 4;
        }
    }

    @Override
    public SwordBoomerang makeCopy() {
        SwordBoomerang newCard = new SwordBoomerang();
        newCard.upgradable = this.upgradable;
        newCard.repeat = this.repeat;
        return newCard;
    }
}
