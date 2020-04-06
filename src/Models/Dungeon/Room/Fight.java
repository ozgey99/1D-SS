package Models.Dungeon.Room;

import Models.Actions.PowerActions;
import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Creatures.Monsters.Temp;
import Models.Dungeon.AbstractRoom;
import Models.Main;
import Models.Object.AbstractPower;
import Models.Object.Powers.Strength;
import Models.Object.Powers.Vulnerable;
import Models.UI;

import java.util.ArrayList;

public class Fight extends AbstractRoom {

    ArrayList<AbstractMonster> monsters;

    private Deck draw;
    private Deck discard;
    private Deck exhaust;
    private Deck hand;
    private int turn;

    private AbstractCharacter player;

    int drawAmount;

    public Fight(AbstractRoom c) {
        type = RoomType.FIGHT;
        children = c;
        done = false;
        monsters = new ArrayList<>();
        generate();
    }

    @Override
    public void start() {
        UI.displayFightStart(this);
        player = Main.game.getPlayer();

        draw = new Deck();
        draw.copyCards(player.masterDeck);
        draw.shuffle();

        discard = new Deck();
        exhaust = new Deck();

        drawAmount = 5;

        turn = 1;

        PowerActions.addPower(player, new Vulnerable());
        PowerActions.addPower(player, new Strength());

        preFight();

        while (true) {

            preTurn();
            if (done) break;
            turn();
            if (done) break;
            postTurn();

            monsterPreTurn();
            if (done) break;
            monsterTurn();
            if (done) break;
            monsterPostTurn();

            turn++;

        }

        postFight();
    }

    private void preFight() {

    }

    private void preTurn() {
        player.recharge();
        player.changeBlock(-player.getBlock());

        if (turn != 1) {
            PowerActions.turnEndDecrease(player);
        }
    }

    private void turn() {
        hand = Deck.drawCard(draw, discard, drawAmount);

        while (true) {
            UI.displayFightInfo(this);

            System.out.println();
            System.out.println();

            UI.displayDeck(hand, "Hand");
            System.out.print("Choose your card: ");

            int card = UI.getInput(-1, hand.getSize());

            if (card == -1) break;

            AbstractCard c = hand.getCard(card);
            if (!c.use(this, player)) {
                System.out.println("You do not have enough energy to use this card.");
            } else {
                hand.removeCard(c);
                discard.addCard(c);
            }

            monsters.removeIf(m -> m.getCurrentHP() <= 0);
            if (monsters.isEmpty()) {
                done = true;
                return;
            }
            System.out.println("---------------------------");
        }

        discard.addDeck(hand);
    }

    private void postTurn() {

    }

    private void monsterPreTurn() {
        for (AbstractMonster m : monsters) {
            m.changeBlock(-m.getBlock());
            PowerActions.turnEndDecrease(m);
        }

    }

    private void monsterTurn() {
        for (AbstractMonster m : monsters) {
            m.act(this, player);
        }

        if (player.getCurrentHP() <= 0) done = true;
    }

    private void monsterPostTurn() {

    }


    private void postFight() {

    }

    private void generate() {
        int act = Main.game.getDungeon().getAct();
        if (act == 1) {
            // change this to random
            monsters.add(new Temp());
            monsters.add(new Temp());
        }
    }

    // Getters and setters.
    // --------------------------------------------------------------------------------------------------------

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

    public ArrayList<AbstractMonster> getMonsters() {
        return monsters;
    }

    public Deck getDraw(){ return draw; }

    public Deck getDiscard(){ return discard; }

    public Deck getExhaust(){ return exhaust; }

    public Deck getHand() { return hand; }

}
