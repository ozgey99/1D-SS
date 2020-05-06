package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;
import View.Controller;

public class PommelStrike extends AbstractCard {

    public PommelStrike(){
        name = "PommelStrike";
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

        selected = false;
        if (!player.changeEnergy(-cost)) return false;

        f.getHand().addDeck(Deck.drawCard(f.getDraw(), f.getDiscard(), baseAttr.draw));

        AbstractMonster monster = Controller.getMonsterInput();
        FightActions.attack(player, monster, baseAttr.damage);

        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            this.baseAttr.damage = 10;
            this.baseAttr.draw = 2;
            this.name = this.name + "+";

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
