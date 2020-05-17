package Models.Cards.Red;

import Controller.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;

public class Combust extends AbstractCard {

    public Combust(){
        name = "Combust";
        description = "At the end of your turn, lose 1 HP and deal 5(7) damage to ALL enemies.";
        cost = 1;
        type = CardType.POWER;
        color = CardColor.RED;
        rarity = CardRarity.UNCOMMON;
        target = CardTarget.ALL_ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 5;
        usable = true;
        upgradable = true;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;
        if (!player.changeEnergy(-cost)) return false;
        for (AbstractMonster m : f.getMonsters()) {
            FightActions.attack(player, m, baseAttr.damage);
        }
        player.changeHealth(-1);
        return true;
    }

    @Override
    public void upgrade() {
        if(upgradable){
            upgradable = false;
            baseAttr.damage = 7;
            this.name = this.name + "+";

        }
    }

    @Override
    public Combust makeCopy() {
        Combust newCard = new Combust();
        newCard.upgradable = this.upgradable;
        newCard.baseAttr.damage = this.baseAttr.damage;
        return newCard;
    }
}
