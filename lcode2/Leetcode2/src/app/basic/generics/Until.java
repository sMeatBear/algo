package app.basic.generics;

public class Until {
    public <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        K k1 = p1.getKey();
        K k2 = p2.getKey();
        return k1.equals(k2);
    }
}