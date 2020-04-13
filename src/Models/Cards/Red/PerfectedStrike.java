package Models.Cards.Red;

import Models.Actions.FightActions;
import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.UI;

public class PerfectedStrike extends AbstractCard {

    private int additionalDamage;

    public PerfectedStrike(){
        name = "Perfected Strike";
        description = "Deal 6 damage. Deals an additional 2(3) damage for ALL of your cards containing \"Strike\".";
        cost = 2;
        type = CardType.ATTACK;
        color = CardColor.RED;
        rarity = CardRarity.COMMON;
        target = CardTarget.ENEMY;
        baseAttr = new BaseCardAttributes();
        baseAttr.damage = 6;
        usable = true;
        upgradable = true;
        additionalDamage = 2;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        if (!player.changeEnergy(-cost)) return false;
        int monster = UI.getInput(0, f.getMonsters().size());
        FightActions.attack(player, f.getMonsters().get(monster), baseAttr.damage);

        for(AbstractCard c : f.getDraw().getCardList()){
            if(c.getName().contains("Strike"))
                FightActions.attack(player, f.getMonsters().get(monster), additionalDamage);
        }

        for(AbstractCard c : f.getHand().getCardList()){
            if(c.getName().contains("Strike"))
                FightActions.attack(player, f.getMonsters().get(monster), additionalDamage);
        }

        for(AbstractCard c : f.getDiscard().getCardList()){
            if(c.getName().contains("Strike"))
                FightActions.attack(player, f.getMonsters().get(monster), additionalDamage);
        }
        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            upgradable = false;
            additionalDamage = 3;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        PerfectedStrike copy = new PerfectedStrike();
        copy.upgradable = this.upgradable;
        copy.additionalDamage = this.additionalDamage;
        return copy;
    }

}
