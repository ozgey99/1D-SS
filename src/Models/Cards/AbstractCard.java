package Models.Cards;

import java.util.ArrayList;

public abstract class AbstractCard {
    public String name;
    public String description;
    public int cost;
    public CardType type;
    public CardColor color;
    public CardRarity rarity;
    public CardTarget target;
    public CardKeyword keyword;
    public BaseCardAttributes baseAttr;
    public boolean usable;
    public boolean upgradable;

    public abstract void use(ArrayList<AbstractMonster> monsters, AbstractCharacter hero);
    public abstract void upgrade();
    public abstract AbstractCard makeCopy();
}
