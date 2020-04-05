package Models.Cards.Red;

import Models.Actions.AttackActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

import java.util.ArrayList;

public class BodySlam extends AbstractCard {
    private String name;
    private String description;
    private int cost;
    private CardType type;
    private CardColor color;
    private CardRarity rarity;
    private CardTarget target;
    private BaseCardAttributes baseAttr;
    private boolean usable;
    private boolean upgradable;

    public BodySlam() {
        name = "Body Slam";
        description = "Deal damage equal to your Block.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;
        int monster = UI.getInput(0, f.getMonsters().size());
        AttackActions.Attack(f.getMonsters().get(monster), player.getBlock());
        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            this.cost = 0;
            this.upgradable = false;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        BodySlam copy = new BodySlam();
        copy.upgradable = this.upgradable;
        copy.cost = this.cost;
        return copy;
    }
}