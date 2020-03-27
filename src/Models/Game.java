package Models;

import Models.Dungeon.Dungeon;

public class Game {
    public static Dungeon dungeon;

    public static void main(String[] args) {
        initialize();
        start();
    }

    public static void initialize() {
        dungeon = new Dungeon();
    }

    public static void start() {
        boolean finished = false;
        UI.displayStartMessage();

        dungeon.getCurrentRoom().start();

        UI.displayFinishMessage();
    }
}