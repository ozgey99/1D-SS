package Models.Object.Relics;

import Models.Actions.PowerActions;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.Powers.Vulnerable;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class BloodVial extends AbstractRelic {

    private int turn;
    public BloodVial(){
        name = "Blood Vial";
        description = "At the start of each combat, heal 2 HP.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
        turn = 0;
        amount = 2;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new BloodVial();
    }

    @Override
    public int onAttack(int prevDamage) { return prevDamage; }

    @Override
    public int onDamage(int prevDamage) {
        return prevDamage;
    }

    @Override
    public int onBlock(int prevBlock) {
        return prevBlock;
    }

    @Override
    public void onDamage(AbstractCreature c) {}

    @Override
    public void onTurnStart(Fight f) {
        turn++;
    }

    @Override
    public void onTurnStart(AbstractCreature c) {
        if(turn == 1 && c instanceof AbstractCharacter)
            c.changeHealth(amount);
    }

}
