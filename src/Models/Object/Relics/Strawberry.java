package Models.Object.Relics;

import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class Strawberry extends AbstractRelic {

    public Strawberry(){
        name = "Strawberry";
        description = "Upon pickup, raise your Max HP by 7.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
        amount = 7;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new Strawberry();
    }

    @Override
    public int onAttack(int prevDamage) { return prevDamage; }

    @Override
    public int onDamage(int prevDamage) {
        return prevDamage;
    }

    @Override
    public int onBlock(int prevBlock) {
        return prevBlock;
    }

    @Override
    public void affect(Fight f, AbstractCharacter player){
        player.changeMaxHP(amount);
    }
}
