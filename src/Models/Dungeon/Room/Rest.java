package Models.Dungeon.Room;

import Models.Creatures.AbstractCharacter;
import Models.Dungeon.AbstractRoom;
import Models.Main;
import Models.UI;

public class Rest extends AbstractRoom {
    public Rest(AbstractRoom c) {
        type = RoomType.REST;
        children = c;
        done = false;
    }

    @Override
    public void start() {
        AbstractCharacter player = Main.game.getPlayer();

        while (!done) {
            UI.displayRestRoom();
            int in = UI.getInput(-1, 1);
            if (in == 0) {
                player.changeHealth(player.getMaxHP() * 3 / 10);
                done = true;
            } else if (in == 1) {
                int amount = UI.displaySmithList(player.masterDeck);
                int choose = UI.getInput(-1, amount);
                if (choose != -1) {
                    player.masterDeck.getCard(choose).upgrade();
                }
                done = true;
            }
        }
    }

    @Override
    public RoomType getType() {
        return type;
    }

    @Override
    public AbstractRoom getChildren() {
        return children;
    }

    @Override
    public boolean getDone() {
        return done;
    }
}
