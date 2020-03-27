package Models;


import Models.Dungeon.Room.Fight;

import java.util.Scanner;

public class UI {
    Scanner in;

    UI() {
        in = new Scanner(System.in);
    }

    public static void displayStartMessage() {
        System.out.println("The game has started.");
    }

    public static void displayFinishMessage() {
        System.out.println("The game has finished.");
    }

    public static void displayFightRoom(Fight f) {
        System.out.println("Fight begins!");
    }
}
