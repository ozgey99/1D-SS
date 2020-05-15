package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;

import java.util.ArrayList;
import java.util.Collections;

public class SwordBoomerang extends AbstractCard {

    private int repeat;

    public SwordBoomerang(){
        name = "SwordBoomerang";
        description = "Deal 3 damage to a random enemy 3(4) times.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 3;
        usable = true;
        upgradable = false;
        repeat = 3;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;
        if (!player.changeEnergy(-cost)) return false;
        ArrayList<AbstractMonster> copy = (ArrayList<AbstractMonster>)(f.getMonsters().clone());
        Collections.shuffle(copy);
        for(int i = 0; i < repeat; i++) {
            FightActions.attack(player, copy.get(0), baseAttr.damage);
        }
        return true;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
            this.name = this.name + "+";
            repeat = 4;
        }
    }

    @Override
    public SwordBoomerang makeCopy() {
        SwordBoomerang newCard = new SwordBoomerang();
        newCard.upgradable = this.upgradable;
        newCard.repeat = this.repeat;
        return newCard;
    }
}