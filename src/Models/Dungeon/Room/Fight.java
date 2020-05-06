package Models.Dungeon.Room;

import Models.Actions.FightActions;
import Models.Actions.PowerActions;
import Models.Cards.AbstractCard;
import Models.Cards.CardColor;
import Models.Cards.CardTarget;
import Models.Cards.Deck;
import Models.Cards.Red.Anger;
import Models.Cards.Red.Bash;
import Models.Cards.Red.Clash;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.*;
import Models.Dungeon.AbstractRoom;
import Models.Object.AbstractPower;
import Models.Utils;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sts.Main;
import Models.Object.AbstractRelic;
import Models.Object.Powers.Strength;
import Models.Object.Powers.Vulnerable;
import Models.TextBasedUI;
import javafx.scene.control.Control;
import sts.Controller;
import sts.FightScene;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import static sts.Main.game;

public class Fight extends AbstractRoom {

    ArrayList<AbstractMonster> monsters;
    ArrayList<AbstractRelic> relics;
    private FightState state;
    private Deck draw;
    private Deck discard;
    private Deck exhaust;
    private Deck hand;
    private int turn;
    private boolean isElite;
    private boolean isBoss;
    private AbstractMonster selectedMonster;
    private AbstractCharacter player;
    int cardRewardAmount;
    int drawAmount;
    int goldAmount;
    ArrayList<AbstractCard> cardRewards;

    public Fight(ArrayList<AbstractRoom> c, boolean isElite, boolean isBoss) {
        type = RoomType.FIGHT;
        this.isBoss = isBoss;
        this.isElite = isElite;
        children = c;
        done = false;
        cardRewards = new ArrayList<>();
        monsters = new ArrayList<>();
        relics = new ArrayList<>();
        generate();
    }
    public void setSelectedMonster(AbstractMonster monster)
    {
        selectedMonster = monster;
    }

    public AbstractMonster getSelectedMonster()
    {
        return selectedMonster;
    }

    public void nextState()
    {
        switch(state) {

            case PREFIGHT:preTurn(); break;
            case PRETURN:turn(); break;
            case TURN:postTurn();break;
            case POSTTURN:monsterPreTurn();break;
            case MONSTERPRETURN:monsterTurn();break;
            case MONSTERTURN:monsterPostTurn();break;
            case MONSTERPOSTTURN:preTurn();break;

        }
    }

    @Override
    public void start() {

        game.currentScene = new FightScene();
        Main.window.setScene(
                game.currentScene);

        System.out.println("I AM IN START");
        player = Main.game.getPlayer();

        draw = new Deck();
        draw.copyCards(player.masterDeck);
        draw.shuffle();

        discard = new Deck();
        exhaust = new Deck();

        drawAmount = 5;
        turn = 1;
        game.currentScene.initialize();

        preFight();


        TextBasedUI.displayFightStart(this);
    }

    private void preFight() {
        System.out.println("I AM IN PREFIGHT");
        state = FightState.PREFIGHT;
        for (AbstractRelic r : player.relics) {
            r.onFightStart(this, player);
        }
        nextState();
    }

    private void preTurn() {
        System.out.println("I AM IN PRETURN");
        state = FightState.PRETURN;
        for (AbstractRelic r : player.relics) {
            r.onTurnStart(this);
            r.onTurnStart(player);
        }

        player.recharge();
        player.resetBlock();

        if (turn != 1) {
            PowerActions.turnEndDecrease(player);
        }
        nextState();
    }

    private void turn() {
        System.out.println("I AM IN TURN");

        state = FightState.TURN;
        hand = Deck.drawCard(draw, discard, drawAmount);
        if(hand == null)
            System.out.println("HAND IS NULL");
        game.currentScene.draw();
        /*
        game.fightScene.draw();
        game.fightScene.lower.draw();
        game.fightScene.right.draw();*/
    }

    public boolean useCard(AbstractCard card)
    {
        System.out.println("I AM USING CARD");
        if(getSelectedMonster() == null && card.getTarget() == CardTarget.ENEMY)
        {
            return false;
        }
        if (!card.use(this, player)) {
            System.out.println("You do not have enough energy to use this card.");
            return false;
        } else {
            TextBasedUI.displayCard(card);
            hand.removeCard(card);
            discard.addCard(card);
           // TextBasedUI.displayDeck(discard, "dıscard");
        }

        monsters.removeIf(m -> m.getCurrentHP() <= 0);
        if (monsters.isEmpty()) {
            done = true;
        }
        setSelectedMonster(null);
        return true;
    }

    private void postTurn() {
        System.out.println("I AM IN POSTTURN");
        state = FightState.POSTTURN;
        nextState();

    }

    private void monsterPreTurn() {
      //  ( (FightScene)(game.currentScene)).drawMonsters();

        System.out.println("I AM IN MONSTERPRETURN");

        state = FightState.MONSTERPRETURN;
        for (AbstractMonster m : monsters) {
            m.resetBlock();
            PowerActions.turnEndDecrease(m);
        }

        for (AbstractMonster m : monsters) {
            ArrayList<AbstractPower> temp = new ArrayList<>(m.powers);



            for (AbstractPower p : temp) {
                p.onTurnStart(this);
                p.onTurnStart(m);
            }
        }
        //for(AbstractMonster m : monsters)
        //{
         //   for(int r = 0; r < m.powers.size();r++)
           // {
             //   System.out.println("MONSTER POWR IS " + m.powers.get(r).getName() + " " + m.powers.get(r).getAmount());
          //  }
         //}

        nextState();

    }

    private void monsterTurn() {
        /*
        for (AbstractMonster m : monsters) {
            for (AbstractPower p : m.powers) {
                p.onTurnStart(m);
            }
        }
*/
        //( (FightScene)(game.currentScene)).drawMonsters();

        System.out.println("I AM IN MOSNTERTURN");
        state = FightState.MONSTERTURN;
        for (AbstractMonster m : monsters) {
            m.act(this, player);
        }

        if (player.getCurrentHP() <= 0) done = true;
        //( (FightScene)(game.currentScene)).drawMonsters();

        nextState();
    }

    private void monsterPostTurn() {
        System.out.println("I AM IN MONSTERPOSTTURN");
        state = FightState.MONSTERPOSTTURN;
        for (AbstractMonster m : monsters) {
            ArrayList<AbstractPower> temp = new ArrayList<>(m.powers);

            for (AbstractPower p : temp) {
                p.onTurnEnd(m);
            }
        }
        nextState();

    }


    private void postFight() {
        System.out.println("I AM IN POSTFIGHT");

        state = FightState.POSTFIGHT;
        player.changeGold(goldAmount);
        for (AbstractRelic r : player.relics) {
            r.onFightEnd(player);
        }


        ArrayList<AbstractPower> temp = new ArrayList<>(player.powers);

        for (AbstractPower p : temp) {
            p.onFightEnd(player);
        }

        nextState();
    }

    private void generate() {
        /**
        int act = Main.game.getDungeon().getAct();
        if (act == 1) {
            // change this
         */

        if(isElite){
            ArrayList<AbstractMonster> elites = Utils.getAllElites();
            Collections.shuffle(elites);

            monsters.add(elites.get(0));

            if(elites.get(0) instanceof Sentry){
                monsters.add(new Sentry());
                monsters.add(new Sentry());
            }
        }
        else if(isBoss){
            ArrayList<AbstractMonster> bosses = Utils.getAllBosses();
            Collections.shuffle(bosses);
            monsters.add(bosses.get(0));
        }
        else{
            ArrayList<AbstractMonster> m = Utils.getAllNonElites();
            Collections.shuffle(m);
            monsters.add(m.get(0));

            if(m.get(0) instanceof RedLouse)
                monsters.add(new GreenLouse());
            else if(m.get(0) instanceof GreenLouse)
                monsters.add(new RedLouse());
        }

        generateRewards();
        relicReward();
    }

    private void generateRewards() {
        ArrayList<AbstractCard> allCards = Utils.getAllCardsOfColor(CardColor.RED);

        Collections.shuffle(allCards);
        for (int i = 0; i < cardRewardAmount; i++){
            cardRewards.add(allCards.get(i));
            break; // sonradan ekledim
        }


        goldAmount = (int) (Math.random() * 100) + 10;
    }

    /*private void generateRewards() {
        ArrayList<AbstractCard> allCards = Utils.getAllCardsOfColor(CardColor.RED);
        Collections.shuffle(allCards);
        cardRewardAmount = 0;
        for (AbstractCard c : allCards) {
            if(cardRewardAmount ==3) break;
            if (!Utils.containsInstance(game.getPlayer().masterDeck.getCardList(), c.getClass())) {
                cardRewards.add(c);
            }
            cardRewardAmount++;
        }
        goldAmount = (int) (Math.random() * 100) + 10;
    }*/

    private void relicReward() {
        ArrayList<AbstractRelic> allRelics = Utils.getAllRelics();
        Collections.shuffle(allRelics);
        for (AbstractRelic r : allRelics) {
            if (!Utils.containsInstance(Main.game.getPlayer().relics, r.getClass())) {
                relics.add(r);
                break;
            }
        }
        goldAmount = (int) (Math.random() * 100) + 10;
    }

    // Getters and setters.
    // --------------------------------------------------------------------------------------------------------

    @Override
    public RoomType getType() {
        return type;
    }

    @Override
    public ArrayList<AbstractRoom> getChildren() {
        return children;
    }

    @Override
    public boolean getDone() {
        return done;
    }

    public ArrayList<AbstractMonster> getMonsters() {
        return monsters;
    }

    public ArrayList<AbstractCard> getCards(){ return cardRewards;}

    public Deck getDraw(){ return draw; }

    public Deck getDiscard(){ return discard; }

    public Deck getExhaust(){ return exhaust; }

    public Deck getHand() { return hand; }

    public int getGoldAmount() {
        return goldAmount;
    }

    public ArrayList<AbstractRelic> getRelics() {
        return relics;
    }

    public boolean getIsElite() { return isElite; }

    public boolean getIsBoss() {
        return isBoss;
    }

}
