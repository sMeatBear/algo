package app.basic.generics;

public class Cooridinate<K, V> implements Pair<K, V> {
    private K key;
    private V value;

    public Cooridinate(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    public boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        K k1 = p1.getKey();
        K k2 = p2.getKey();
        return k1.equals(k2);
    }
}