package Models.Actions;

import Models.Creatures.AbstractCharacter;
import Models.Object.AbstractRelic;
import Models.Utils;

public class RelicActions {

    public static void addRelic(AbstractCharacter c, AbstractRelic relic) {
        if(!Utils.containsInstance(c.relics, relic.getClass()))
            c.relics.add(relic);
    }
    public static void removeRelic(AbstractCharacter c, AbstractRelic relic) {
        AbstractRelic r = Utils.getInstance(c.relics, relic.getClass());
        if (r != null) {
            c.relics.remove(r);
        }
    }
}
