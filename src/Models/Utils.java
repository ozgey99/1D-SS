package Models;

import java.util.List;

public class Utils {
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
