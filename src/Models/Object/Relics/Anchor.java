package Models.Object.Relics;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class Anchor extends AbstractRelic {

    private int turn;
    public Anchor(){
        name = "Anchor";
        description = "Start each combat with 10 Block.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
        turn = 0;
        amount = 10;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new Anchor();
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
            c.changeBlock(amount);
        }
    }
}
