package Models.Object.Relics;

import Models.Actions.PowerActions;
import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.Powers.Strength;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class Vajra extends AbstractRelic {

    private int turn;
    public Vajra(){
        name = "Vajra";
        description = "At the start of each combat, gain 1 Strength.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
        turn = 0;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new Vajra();
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
        if(turn == 1){
            PowerActions.addPower(c, new Strength());
        }
    }

}
