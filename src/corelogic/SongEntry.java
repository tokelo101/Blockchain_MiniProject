package corelogic;

/**
 * 
 * @author TM Monare 221022037
 *
 * @param <K> key for SongEntry
 * @param <V> values for songEntry
 */
public class SongEntry<K,V> implements Entry<K, V>{
    protected K key;
    protected V value;

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