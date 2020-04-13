package Models.Object.Relics;

import Models.Creatures.AbstractCharacter;
import Models.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class Mango extends AbstractRelic {

    public Mango(){
        name = "Mango";
        description = "Upon pickup, raise your Max HP by 14.";
        rarity = RelicRarity.RARE;
        rClass = RelicClass.ANY;
        amount = 14;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new Mango();
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
