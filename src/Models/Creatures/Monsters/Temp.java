package Models.Creatures.Monsters;

public class Temp extends AbstractMonster {
    public Temp() {
        name = "Temporary Monster";
        maxHP = 10;
        currentHP = maxHP;
        initialize();
    }

    @Override
    public void initialize() {
        MonsterMove attk = new MonsterMove(this);
        attk.setDamage(5);

        MonsterMove blk = new MonsterMove(this);
        blk.setBlock(5);

        MonsterMove both = new MonsterMove(this);
        both.setDamage(10);
        both.setBlock(10);

        moveList.add(attk);
        moveList.add(blk);
        moveList.add(both);

    }
}
