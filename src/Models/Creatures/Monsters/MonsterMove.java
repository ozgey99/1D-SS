package Models.Creatures.Monsters;

import Models.Creatures.AbstractCreature;
import Models.Object.AbstractPower;

import java.util.ArrayList;

public class MonsterMove {
    AbstractMonster owner;
    int damage;
    int block;
    boolean isSelf;
    ArrayList<AbstractPower> powers;

    public MonsterMove(AbstractMonster o) {
        owner = o;
        powers = new ArrayList<>();
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
}
