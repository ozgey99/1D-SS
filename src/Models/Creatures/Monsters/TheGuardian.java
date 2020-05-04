package Models.Creatures.Monsters;

import Models.Object.Powers.Vulnerable;
import Models.Object.Powers.Weak;

import java.util.ArrayList;

public class TheGuardian extends AbstractMonster {
    public TheGuardian(){
        name = "TheGuardian";
        maxHP = 240;
        currentHP = maxHP;
        initialize();
    }

    @Override
    public void initialize() {
        moveList = new ArrayList<MonsterMove>();

        for(int i = 0; i < 50; i++) {
            MonsterMove chargingUp = new MonsterMove(this);
            chargingUp.setBlock(9);
            moveList.add(chargingUp);

            MonsterMove fierceBash = new MonsterMove(this);
            fierceBash.setDamage(32);
            moveList.add(fierceBash);

            MonsterMove ventSteam = new MonsterMove(this);
            ventSteam.setSelf(false);
            Vulnerable v = new Vulnerable();
            v.stack(1);
            Weak w = new Weak();
            w.stack(1);
            ventSteam.addPower(v);
            ventSteam.addPower(w);
            moveList.add(ventSteam);

            MonsterMove whirlWind1 = new MonsterMove(this);
            whirlWind1.setDamage(4);
            moveList.add(whirlWind1);

            MonsterMove whirlWind2 = new MonsterMove(this);
            whirlWind2.setDamage(4);
            moveList.add(whirlWind2);

            MonsterMove whirlWind3 = new MonsterMove(this);
            whirlWind3.setDamage(4);
            moveList.add(whirlWind3);

            MonsterMove whirlWind4 = new MonsterMove(this);
            whirlWind4.setDamage(4);
            moveList.add(whirlWind4);

            MonsterMove whirlWind5 = new MonsterMove(this);
            whirlWind1.setDamage(4);
            moveList.add(whirlWind5);
        }
    }
}
