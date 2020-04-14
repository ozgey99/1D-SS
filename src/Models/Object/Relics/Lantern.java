package Models.Object.Relics;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class Lantern extends AbstractRelic {

    private int turn;
    public Lantern(){
        name = "Lantern";
        description = "Gain 1 Energy on the first turn of each combat.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
        amount = 1;
        turn = 0;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new Lantern();
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
        if(turn == 1 && c instanceof AbstractCharacter){
            ((AbstractCharacter) c).changeEnergy(amount);
        }
    }
}
