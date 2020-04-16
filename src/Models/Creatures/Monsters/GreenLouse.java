package Models.Creatures.Monsters;

import Models.Object.Powers.Weak;
import Models.Utils;

public class GreenLouse extends AbstractMonster {
    public GreenLouse() {
        name = "Green Louse";
        maxHP = Utils.random(6, 11);
        currentHP = maxHP;
        initialize();
    }

    @Override
    public void initialize() {
        MonsterMove bite = new MonsterMove(this);
        bite.setDamage(Utils.random(2, 5));

        MonsterMove spitWeb = new MonsterMove(this);
        Weak weak = new Weak();
        weak.stack(1);
        spitWeb.addPower(weak);

        for (int i = 0; i < 20; i++) {
            int random = Utils.random(100, 0);
            if (random <= 25) {
                moveList.add(spitWeb);
            } else {
                moveList.add(bite);
            }
        }
    }
}
