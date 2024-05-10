package corelogic;

public class SongEntry<K,V> implements Entry<K, V>{
    protected K key;
    protected V value;
    //protected Position<Entry<K, V>> pos;

    public SongEntry(K k, V v) {
        key = k;
        value = v;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}