package Models.Creatures;

import Models.Object.AbstractPower;

import java.util.ArrayList;

public abstract class AbstractCreature {
    protected int maxHP;
    protected int currentHP;
    protected int block;
    public ArrayList<AbstractPower> powers = new ArrayList<AbstractPower>();

    public void changeHealth(int change) {
        currentHP = currentHP + change;
        if(currentHP < 0) {
            currentHP = 0;
        }
        else if( currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

    public void changeBlock(int b) {
        block += b;
    };

    public void initialize() {
        maxHP = 100;
        currentHP = 100;
    }

    public void resetBlock() {
        block = 0;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getBlock() {
        return block;
    }
}
