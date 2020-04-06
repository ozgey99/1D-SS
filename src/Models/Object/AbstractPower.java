package Models.Object;

public abstract class AbstractPower extends AbstractObject {
    protected String name;
    protected String description;
    protected int amount;
    public boolean doesDecreaseEachTurn;
    public boolean when;

    public void stack(int a) {
        amount += a;
    }

    public abstract AbstractPower makeCopy();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }
}
