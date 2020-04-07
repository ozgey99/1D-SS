package Models.Actions;

import Models.Creatures.AbstractCharacter;
import Models.Object.AbstractRelic;

public class RelicActions {

    public static void addRelic(AbstractCharacter c, AbstractRelic relic) {
        if(!c.relics.contains(relic))
            c.relics.add(relic);
    }
    public static void removeRelic(AbstractCharacter c, AbstractRelic relic) {
        c.relics.remove(relic);
    }
}
