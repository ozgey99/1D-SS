package Models.Creatures;

public class Pet extends AbstractCreature {
    private int damage;

    public Pet(){
        damage = 5;
    }
    public void upgrade(){
        damage += 5;
    }

    public int getDamage(){
        return damage;
    }
}
