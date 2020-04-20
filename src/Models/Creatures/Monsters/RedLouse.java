package Models.Creatures.Monsters;

import Models.Object.Powers.Strength;

import java.util.ArrayList;

public class RedLouse extends AbstractMonster {
    public RedLouse() {
        name = "Red Louse";
        maxHP = (int) (Math.random() * 5 + 10);
        currentHP = maxHP;
        initialize();
    }

    @Override
    public void initialize() {
        moveList = new ArrayList<MonsterMove>();
        MonsterMove bite = new MonsterMove(this);
        bite.setDamage((int) (Math.random() * 2 + 5));

        MonsterMove grow = new MonsterMove(this);
        Strength s = new Strength();
        s.stack(2);
        grow.addPower(s);
        grow.setSelf(true);

        for (int i = 0; i < 20; i++) {
            int random = (int) (Math.random() * 100);
            if (random <= 25) {
                moveList.add(grow);
            } else {
                moveList.add(bite);
            }
        }
    }
}
