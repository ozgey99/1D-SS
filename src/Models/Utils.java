package Models;

import Models.Cards.AbstractCard;
import Models.Cards.CardColor;
import Models.Cards.Colorless.Dazed;
import Models.Cards.Red.*;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.*;
import Models.Object.AbstractPotion;
import Models.Object.AbstractRelic;
import Models.Object.Potions.AgilityPotion;
import Models.Object.Potions.EntropicBrew;
import Models.Object.Powers.Agility;
import Models.Object.Relics.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static int random(int range, int min) {
        return (int) (Math.random() * range + min);
    }

    // This is terrible.
    public static ArrayList<AbstractCard> getAllCardsOfColor(CardColor color) {
        ArrayList<AbstractCard> c = new ArrayList<>();
        c.add(new Anger());
        c.add(new Bash());
        c.add(new Bloodletting());
        c.add(new Bludgeon());
        c.add(new BodySlam());
        c.add(new Clash());
        c.add(new Cleave());
        c.add(new Combust());
        c.add(new Dazed()); // -------
        c.add(new Entrench());
        c.add(new Havoc());
        c.add(new Hemokinesis());
        c.add(new Inflame());
        c.add(new IronWave());
        c.add(new PerfectedStrike());
        c.add(new SearingBlow());
        c.add(new ShrugItOff());
        c.add(new SwordBoomerang());
        c.add(new Thunderclap());
        c.add(new TwinStrike());

        ArrayList<AbstractCard> res = new ArrayList<>();
        for (AbstractCard card : c) {
            if (card.getColor() == color) res.add(card);
        }

        return res;
    }


    public static ArrayList<AbstractRelic> getAllRelics() {
        ArrayList<AbstractRelic> r = new ArrayList<>();
        r.add(new Anchor());
        r.add(new BagOfMarbles());
        r.add(new BloodVial());
        r.add(new HappyFlower());
        r.add(new Lantern());
        r.add(new Vajra());

        return r;
    }

    public static ArrayList<AbstractPotion> getAllPotions() {
        ArrayList<AbstractPotion> p = new ArrayList<>();
        p.add(new AgilityPotion());
        p.add(new EntropicBrew());

        return p;
    }

    public static ArrayList<AbstractMonster> getAllBosses(){
        ArrayList<AbstractMonster> bosses = new ArrayList<>();
        bosses.add(new TheGuardian());
        return bosses;
    }

    public static ArrayList<AbstractMonster> getAllElites(){
        ArrayList<AbstractMonster> elites = new ArrayList<>();
        elites.add(new Sentry());
        elites.add(new GremlinNob());
        return elites;
    }

    public static ArrayList<AbstractMonster> getAllNonElites(){
        ArrayList<AbstractMonster> m = new ArrayList<>();
        m.add(new Cultist());
        m.add(new GreenLouse());
        m.add(new JawWorm());
        m.add(new RedLouse());
        return m;
    }


    public static <E> boolean containsInstance(List<E> l, Class<? extends E> c) {
        for (E e : l) {
            if (c.isInstance(e)) return true;
        }
        return false;
    }

    public static <E> E getInstance(List<E> l, Class<? extends E> c) {
        for (E e : l) {
            if (c.isInstance(e)) return e;
        }
        return null;
    }
}
