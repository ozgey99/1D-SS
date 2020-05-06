package Models.Object.Relics;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Controller.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class HappyFlower extends AbstractRelic {

    private int turn;
    public HappyFlower(){
        name = "HappyFlower";
        description = "Every 3 turns, gain 1 Energy.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
        amount = 1;
        turn = 0;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new HappyFlower();
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
        if(turn % 3 == 0 && c instanceof AbstractCharacter){
            ((AbstractCharacter) c).changeEnergy(amount);
        }
    }
}
