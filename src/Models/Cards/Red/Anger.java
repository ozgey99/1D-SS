package Models.Cards.Red;

import Models.Cards.*;

import java.util.ArrayList;

public class Anger extends AbstractCard {
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

    public Anger() {
        name = "Anger";
        description = "Deal 6 damage. Add a copy of this card into your discard pile.";
        cost = 0;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 6;
        usable = true;
        upgradable = true;
    }

    @Override
    public void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero) {
        for (AbstractMonster m : monsters) {
            m.changeHealth(-baseAttr.damage);
        }
        Deck discardPile = ((Fight)Dungeon.currentRoom).discardPile;
        discardPile.addCard((this.makeCopy()));
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            this.baseAttr.damage = 8;
            upgradable = false;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        Anger copy = new Anger();
        copy.upgradable = upgradable;
        copy.baseAttr.damage = this.baseAttr.damage;
        return  copy;
    }
}
