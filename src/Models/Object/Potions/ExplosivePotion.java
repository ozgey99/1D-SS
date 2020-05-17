package Models.Object.Potions;

import Controller.Actions.FightActions;
import Controller.Dungeon.Room.Fight;
import Models.Creatures.AbstractCharacter;
import Models.Object.AbstractPotion;
import Models.Object.PotionType;

public class ExplosivePotion extends AbstractPotion {
    public ExplosivePotion() {
        name = "Explosive Potion";
        description = "Deal 10 Damage to all enemies.";
        type = PotionType.DIRECT;
    }

    @Override
    public void use(Fight f, AbstractCharacter player) {
        super.use(f, player);

        for (int i = 0; i < f.getMonsters().size(); i++) {
            FightActions.attack(null, f.getMonsters().get(i), 10);
        }
    }
}
