package Models.Creatures.Monsters;
//import java.util.ArrayList;

import Controller.Actions.FightActions;
import Controller.Actions.PowerActions;
import Models.Cards.AbstractCard;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.AbstractCreature;
import Controller.Dungeon.Room.Fight;
import Models.Object.AbstractPower;

import java.util.ArrayList;

public abstract class AbstractMonster extends AbstractCreature {
    String name;
    boolean selected = false;
    ArrayList<MonsterMove> moveList;

//    void addMove(AbstractPower monsterPower)
//    {
//        moveList.add(monsterPower);
//    }
//    void popMove(AbstractPower executedPower){ moveList.remove( executedPower); }

    public void act(Fight f, AbstractCharacter player) {
        System.out.println(name);
        MonsterMove next = getNextMoveAndRotate();
        FightActions.attack(this, player, next.getDamage());
        FightActions.block(next.owner, next.getBlock());

        for (AbstractPower p : next.powers) {
            PowerActions.addPower((next.isSelf) ? next.owner : player, p);
        }

        for(int r = 0; r < powers.size();r++)
        {
            System.out.println("MONSTER POWR IS " + powers.get(r).getName() + " " + powers.get(r).getAmount());

        }

        for(AbstractCard c : next.addToDiscard){
            f.getDiscard().addCard(c);
        }
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

    public boolean getSelected(){
        return selected;
    }

    public void setSelected(){
        selected = !selected;
    }
}
