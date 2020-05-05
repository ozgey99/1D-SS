package Models.Creatures.Monsters;

import Models.Cards.Colorless.Dazed;

import java.util.ArrayList;

public class Sentry extends AbstractMonster {
    public Sentry(){
        name = "Sentry";
        maxHP = (int) (Math.random() * 5 + 38);
        currentHP = maxHP;
        initialize();
    }

    @Override
    public void initialize() {
        moveList = new ArrayList<MonsterMove>();
        MonsterMove bolt = new MonsterMove(this);
        bolt.addToDiscard(new Dazed());
        bolt.addToDiscard(new Dazed());

        for (int i = 0; i < 50; i++) {
            MonsterMove beam = new MonsterMove(this);
            beam.setDamage(9);
            moveList.add(beam);
        }
    }
}
