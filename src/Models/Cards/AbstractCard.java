package Models.Cards;

import Models.Creatures.AbstractCharacter;
import Controller.Dungeon.Room.Fight;

public abstract class AbstractCard {
    protected String name;
    protected boolean selected;
    protected String description;
    protected int cost;
    protected CardType type;
    protected CardColor color;
    protected CardRarity rarity;
    protected CardTarget target;
    protected CardKeyword keyword;
    protected BaseCardAttributes baseAttr;
    protected boolean usable;
    protected boolean upgradable;

    public abstract boolean use(Fight f, AbstractCharacter player);
    public abstract void upgrade();
    public abstract AbstractCard makeCopy();

    public String getName() {
        return name;
    }

    public CardColor getColor() {
        return color;
    }

    public CardType getType() {
        return type;
    }

    public CardRarity getRarity() {
        return rarity;
    }

    public int getCost() {
        return cost;
    }

    public CardTarget getTarget() {
        return target;
    }

    public CardKeyword getKeyword() {
        return keyword;
    }

    public BaseCardAttributes getBaseAttr() {
        return baseAttr;
    }

    public boolean isUsable() {
        return usable;
    }

    public boolean isUpgradable() {
        return upgradable;
    }

    public String getDescription() {
        return description;
    }

    public boolean getSelected(){
        return selected;
    }

    public void setSelected(){
        selected = !selected;
    }
}
