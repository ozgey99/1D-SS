package Models.Creatures.Monsters;

import Models.Creatures.AbstractCreature;

public class MonsterMove {
    AbstractMonster owner;
    int damage;
    int block;
//    ArrayList<Power> powerOnMove;
    AbstractCreature target;
//    public MonsterMove(){
//        powerOnMove = new ArrayList<Power>();
//    }

    public MonsterMove(AbstractMonster o) {
        owner = o;
    }

    AbstractMonster getOwner()
    {
        return owner;
    }
    AbstractCreature getTarget(){
        return target;
    }
    public int getDamage()
    {
        return damage;
    }
    public int getBlock()
    {
        return block;
    }
    void setDamage( int damage )
    {
        this.damage = damage;
    }
    void setBlock( int block )
    {
        this.block = block;
    }
//    void addPower( Power power )
//    {
//        powerOnMove.add(power);
//    }
}
