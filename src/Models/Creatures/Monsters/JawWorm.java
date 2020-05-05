package Models.Creatures.Monsters;

import Models.Object.Powers.Strength;

import java.util.ArrayList;

public class JawWorm extends AbstractMonster {
    public JawWorm() {
        name = "JawWorm";
        maxHP = (int) (Math.random() * 4 + 40);
        currentHP = maxHP;
        initialize();
    }

    @Override
    public void initialize() {
        moveList = new ArrayList<MonsterMove>();
        MonsterMove chomp = new MonsterMove(this);
        chomp.setDamage(11);

        MonsterMove thrash = new MonsterMove(this);
        thrash.setDamage(7);
        thrash.setBlock(5);

        MonsterMove bellow = new MonsterMove(this);
        Strength s = new Strength();
        s.stack(2);
        bellow.addPower(s);
        bellow.setBlock(6);
        bellow.isSelf = true;

        System.out.println(moveList == null);

        moveList.add(chomp);


        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 100);
            if (random <= 45) {
                moveList.add(bellow);
            } else if (random <= 75) {
                moveList.add(thrash);
            } else {
                moveList.add(chomp);
            }
        }
    }
}
