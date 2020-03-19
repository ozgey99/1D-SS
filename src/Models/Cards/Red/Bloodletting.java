package Models.Cards.Red;

import Models.Cards.*;

import java.util.ArrayList;

public class Bloodletting extends AbstractCard {
    private String name;
    private String description;
    private CardType type;
    private CardColor color;
    private CardRarity rarity;
    private CardTarget target;
    private boolean usable;
    private boolean upgradable;
    private int HPdecrease;
    private int energyIncrease;

    public Bloodletting(){
        name = "Bloodletting";
        description = "Lose 3 HP. Gain 1(2) Energy.";
        type = CardType.SKILL;
        color = CardColor.RED;
        rarity = CardRarity.UNCOMMON;
        target = CardTarget.SELF;
        usable = true;
        upgradable = true;
        HPdecrease = 3;
        energyIncrease = 1;
    }

    @Override
    public void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero) {
        hero.currentEnergy += energyIncrease;
        hero.currentHP -= HPdecrease;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
            energyIncrease = 2;
        }
    }

    @Override
    public Bloodletting makeCopy() {
        Bloodletting newCard = new Bloodletting();
        newCard.upgradable = this.upgradable;
        newCard.energyIncrease = this.energyIncrease;
        return newCard;
    }
}
