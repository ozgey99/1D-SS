package Models.Creatures.Monsters;

import Models.Object.Powers.Ritual;

import java.util.ArrayList;

public class Cultist extends AbstractMonster {
    public Cultist() {
        name = "Cultist";
        maxHP = (int) (Math.random() * 6 + 48);
        currentHP = maxHP;
        initialize();
    }

    @Override
    public void initialize() {
        moveList = new ArrayList<MonsterMove>();
        MonsterMove ritual = new MonsterMove(this);
        ritual.setSelf(true);
        Ritual r = new Ritual();
        r.stack(2);
        ritual.addPower(r);
        moveList.add(ritual);

        for (int i = 0; i < 50; i++) {
            MonsterMove darkStrike = new MonsterMove(this);
            darkStrike.setDamage(6);
            moveList.add(darkStrike);
        }
    }
}
