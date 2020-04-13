package Models.Creatures.Monsters;
//import java.util.ArrayList;

import Models.Actions.FightActions;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Models.Dungeon.Room.Fight;

import java.util.ArrayList;

public abstract class AbstractMonster extends AbstractCreature {
    String name;
    ArrayList<MonsterMove> moveList;

    public AbstractMonster(){
        moveList = new ArrayList<MonsterMove>();
    }
//    void addMove(AbstractPower monsterPower)
//    {
//        moveList.add(monsterPower);
//    }
//    void popMove(AbstractPower executedPower){ moveList.remove( executedPower); }

    public void act(Fight f, AbstractCharacter player) {
        MonsterMove next = getNextMoveAndRotate();
        FightActions.attack(this, player, next.getDamage());
        changeBlock(next.getBlock());
    }

    public MonsterMove getNextMove() {
        return moveList.get(0);
    }

    public MonsterMove getNextMoveAndRotate() {
        MonsterMove m = moveList.get(0);
        moveList.remove(0);
        moveList.add(m);
        return m;
    }

    public String getName() {
        return name;
    }
}
