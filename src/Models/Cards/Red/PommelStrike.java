package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

public class PommelStrike extends AbstractCard {

    public PommelStrike(){
        name = "Pommel Strike";
        description = "Deal 9(10) damage. Draw 1(2) card(s).";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 9;
        baseAttr.draw = 1;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;

        f.getHand().addDeck(Deck.drawCard(f.getDraw(), f.getDiscard(), baseAttr.draw));

        int monster = UI.getInput(0, f.getMonsters().size());
        FightActions.Attack(player, f.getMonsters().get(monster), baseAttr.damage);

        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.baseAttr.damage = 10;
            this.baseAttr.draw = 2;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        PommelStrike copy = new PommelStrike();
        copy.upgradable = this.upgradable;
        copy.baseAttr.damage = this.baseAttr.damage;
        copy.baseAttr.draw = this.baseAttr.draw;
        return copy;
    }
}
