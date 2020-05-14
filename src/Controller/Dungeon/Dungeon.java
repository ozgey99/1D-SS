package Controller.Dungeon;

import Controller.Dungeon.Room.Fight;
import Controller.Dungeon.Room.Merchant;
import Controller.Dungeon.Room.Rest;
import Controller.Dungeon.Room.Treasure;
import View.FightScene;
import View.Main;
import View.MapScene;

import java.security.spec.ECField;
import java.util.ArrayList;

import static View.Main.game;

public class Dungeon {
    private int act;
    private String name;

    private AbstractRoom currentRoom;
    private AbstractRoom beginningRoom;


    public Dungeon() {
        this.act = 1;
        this.name = "The Exordium";
    }

    public void
    generate() {
        //Assign the max number of paths
        int maxPath = 3;
        //Randomize the number of paths
        int noOfPaths = (int) (Math.random() * maxPath) + 2;

        //Assign the max path length
        int maxLength = 3;
        //Randomize the length of paths
        int branchLength = (int) (Math.random() * maxLength) + 2;


        //Assign the maximum no of unions and prohibit 1;
        int numOfUnions = 2;
        if( numOfUnions == 0)
        {
            throw new IllegalArgumentException("Change number of unions");
        }

        //Create boss
        Fight boss = new Fight(null, false, true, false);
        boss.setUnion(true);

        ArrayList<AbstractRoom> prevRooms = new ArrayList<>();
        ArrayList<AbstractRoom> children;
        ArrayList<AbstractRoom> unionChild = new ArrayList<>();
        //Populate from top to bottom
        for( int k = 0; k < numOfUnions; k++ ) {
            prevRooms = new ArrayList<>();
            for (int i = 0; i < noOfPaths; i++) {
                children = new ArrayList<>();
                if (k == 0) {
                    unionChild.add(boss);
                }
                for (int j = 0; j < branchLength; j++) {
                    AbstractRoom room;
                    if (j == 0) {
                        room = randomRoom(unionChild);
                    } else {
                        room = randomRoom(children);
                    }

                    if (j == branchLength - 1) {
                        prevRooms.add(room);
                    }

                    children = new ArrayList<>();
                    children.add(room);
                }
            }
            if (k != numOfUnions - 1) {
                AbstractRoom unionRoom = randomRoom(prevRooms);
                unionRoom.setUnion(true);
                unionChild = new ArrayList<>();
                unionChild.add(unionRoom);
            }
        }

        //add the beginning fight and chest
        Treasure beginningTreasure = new Treasure(prevRooms);

        ArrayList<AbstractRoom> c = new ArrayList<>();
        c.add(beginningTreasure);

        Fight beginningFight = new Fight(c, false, false, true);

        ArrayList<AbstractRoom> childOfEmpty = new ArrayList<>();
        childOfEmpty.add(beginningFight);

        Fight emptyFight = new Fight(childOfEmpty,false, false, false);
        beginningRoom = beginningFight;
        currentRoom = emptyFight;
    }

    public AbstractRoom getBeginningRoom(){
        return beginningRoom;
    }


    private AbstractRoom randomRoom( ArrayList<AbstractRoom> children ){
        AbstractRoom room;
        int rand = (int) (Math.random() * 5) + 1; //for 5 room types
        switch(rand){
            case 1:
                room = new Fight(children, true, false, false);
                break;
            case 2:
                room = new Merchant(children);
                break;
            case 3:
                room = new Rest(children);
                break;
            case 4:
                room = new Treasure(children);
                break;
            default:
                room = new Fight(children, false, false, false);
                break;
        }
        return room;
    }

    public int getAct() {
        return act;
    }

    public String getName() {
        return name;
    }

    public AbstractRoom getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom( AbstractRoom currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean ascend() {
        addTreasure();
        game.currentScene = new MapScene();
        Main.window.setScene(game.currentScene);
        return true;
    }
    public void addTreasure(){
        if (this.currentRoom.getChildren() == null) {
            System.out.println("ASCEND RETURN FALSE");
            return;
        }
        if(currentRoom instanceof Fight){
            ((Fight) currentRoom).generateRewards();
            ((Fight) currentRoom).relicReward();
        }
        else if(currentRoom instanceof Merchant){
            ((Merchant)currentRoom).generate();
        }
        else if(currentRoom instanceof Treasure){
            ((Treasure)currentRoom).relicReward();
            ((Treasure)currentRoom).cardReward();
        }
    }
}
