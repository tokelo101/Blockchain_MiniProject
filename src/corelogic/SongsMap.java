package corelogic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * a Map that stores a collection of Songs identified by an ISRC
 * @author TM Monare 221022037
 */
public class SongsMap<K, V>{
	
	private ArrayList<SongEntry<K,V>> songs = new ArrayList<SongEntry<K,V>>();
	private int size = 0;
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public int size() {
		return size;
	}
	
	/**
	 * 
	 * @param key the key to search for the value to be returned
	 * @return return the value if found, null otherwise
	 */
	public V get(K key) {
		if(isEmpty()) {
			return null;
		}
		for(Entry<K,V> song: songs) {
			if(key.equals(song.getKey())) {
				return song.getValue();
			}
		}	
		return null;
	}
	
	/**
	 * 
	 * @param key the key for the value to be inserted
	 * @param value the value to be inserted
	 * @return return the replaced value if found and null otherwise
	 */
	public V put(K key, V value) {
		SongEntry<K,V> newEntry = new SongEntry<K,V>(key, value);
		
		for(SongEntry<K,V> song: songs) {
			if(key.equals(song.getKey())) {
				V temp = song.getValue();
				songs.remove(song);
				songs.add(newEntry);
				return temp;
			}
		}
		songs.add(newEntry);
		size++;
		return null;
	}
	
	/**
	 * 
	 * @param key the key to search for the value to be removed
	 * @return return the removed value if found, null otherwise
	 */
	public V remove(K key) {
		for(SongEntry<K,V> song: songs) {
			if(key.equals(song.getKey())) {
				V temp = song.getValue();
				songs.remove(song);
				size--;
				return temp;
			}
		}
		return null;
	}
	
	public Iterator<V> values(){
		
		ArrayList<V> values = new ArrayList<>();
		
		for(Entry<K,V> entry: songs) {
			values.add(entry.getValue());
		}
		
		return values.iterator();
	}

}
