package Models;

public class Main {
    public static Game game = new Game();

    public static void main(String[] args) {
        game.getDungeon().generate();
        game.start();
    }
}
