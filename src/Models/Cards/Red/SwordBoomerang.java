package Models.Cards.Red;

import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;

import java.util.ArrayList;
import java.util.Collections;

public class SwordBoomerang extends AbstractCard {

    private int repeat;

    public SwordBoomerang(){
        name = "Sword Boomerang";
        description = "Deal 3 damage to a random enemy 3(4) times.";
        cost = 1;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 3;
        usable = true;
        upgradable = true;
        repeat = 3;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;
        ArrayList<AbstractMonster> copy = (ArrayList<AbstractMonster>)(f.getMonsters().clone());
        Collections.shuffle(copy);
        for(int i = 0; i < repeat; i++) {
            copy.get(0).changeHealth(-baseAttr.damage);
        }
        return true;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
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