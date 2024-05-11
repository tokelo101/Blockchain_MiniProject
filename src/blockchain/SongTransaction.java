package blockchain;

import corelogic.Song;

public class SongTransaction {
	private Song song;
	private String TransactionType;
	
	public SongTransaction(Song song, String TransactionType) {
		this.song = song;
		this.TransactionType = TransactionType;
	}
	
	public Song getSong() {
		return song;
	}
	
	public String getTransactionType() {
		return TransactionType;
	}
}
