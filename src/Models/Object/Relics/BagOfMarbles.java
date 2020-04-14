package Models.Object.Relics;

import Models.Actions.PowerActions;
import Models.Creatures.AbstractCreature;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.Powers.Vulnerable;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class BagOfMarbles extends AbstractRelic {

    private int turn;
    public BagOfMarbles(){
        name = "Bag of Marbles";
        description = "At the start of each combat, apply 1 Vulnerable to ALL enemies.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
        turn = 0;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new BagOfMarbles();
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
        if(turn == 1){
            for(AbstractMonster m : f.getMonsters()){
                PowerActions.addPower(m, new Vulnerable());
            }
        }
    }

    @Override
    public void onTurnStart(AbstractCreature c) {}

}
