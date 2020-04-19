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

}