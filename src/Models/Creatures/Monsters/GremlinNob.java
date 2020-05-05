package Models.Creatures.Monsters;

import Models.Object.Powers.Strength;
import Models.Object.Powers.Vulnerable;

import java.util.ArrayList;

public class GremlinNob extends AbstractMonster {
    public GremlinNob(){
        name = "GremlinNob";
        maxHP = (int) (Math.random() * 5 + 82);
        currentHP = maxHP;
        initialize();
    }

    @Override
    public void initialize() {
        moveList = new ArrayList<MonsterMove>();
        MonsterMove bellow = new MonsterMove(this);
        bellow.setSelf(true);
        Strength enrage = new Strength();
        enrage.stack(1);
        bellow.addPower(enrage);
        moveList.add(bellow);

        for(int i = 0; i < 50; i++){
            MonsterMove rush1 = new MonsterMove(this);
            rush1.setDamage(14);
            moveList.add(rush1);

            MonsterMove rush2 = new MonsterMove(this);
            rush2.setDamage(14);
            moveList.add(rush2);

            MonsterMove skullBash = new MonsterMove(this);
            skullBash.setDamage(6);
            skullBash.isSelf = false;
            Vulnerable v = new Vulnerable();
            v.stack(1);
            skullBash.addPower(v);
            moveList.add(skullBash);
        }
    }
}
