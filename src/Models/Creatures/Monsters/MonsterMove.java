package Models.Creatures.Monsters;

import Models.Cards.AbstractCard;
import Models.Creatures.AbstractCreature;
import Models.Object.AbstractPower;

import java.io.Serializable;
import java.util.ArrayList;

public class MonsterMove implements Serializable {
    AbstractMonster owner;
    int damage;
    int block;
    boolean isSelf;
    ArrayList<AbstractPower> powers;
    ArrayList<AbstractCard> addToDiscard;

    public MonsterMove(AbstractMonster o) {
        owner = o;
        powers = new ArrayList<>();
        addToDiscard = new ArrayList<>();
        isSelf = false;
    }

    AbstractMonster getOwner()
    {
        return owner;
    }
    boolean getIsSelf() { return isSelf; }
    public int getDamage()
    {
        return damage;
    }
    public int getBlock()
    {
        return block;
    }

    public ArrayList<AbstractPower> getPowers() {
        return powers;
    }

    public ArrayList<AbstractCard> getAddToDiscard(){
        return addToDiscard;
    }

    void setDamage(int damage )
    {
        this.damage = damage;
    }
    void setBlock( int block )
    {
        this.block = block;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public void addPower(AbstractPower p) {
        powers.add(p);
    }

    public void addToDiscard(AbstractCard c){
        addToDiscard.add(c);
    }

}
