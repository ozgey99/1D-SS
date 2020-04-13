package Models;

import Models.Cards.AbstractCard;
import Models.Cards.CardColor;
import Models.Cards.Red.*;

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
