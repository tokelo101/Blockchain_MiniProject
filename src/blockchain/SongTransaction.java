package blockchain;

import acsse.csc03a3.Transaction;
import corelogic.Song;

public class SongTransaction<T> extends Transaction<T>{
	private Song song;
	private String transactionType;
	private String sender;
	private String receiver;
	private long timeStamp;
	
	@SuppressWarnings("unchecked")
	public SongTransaction(Song song, String transactionType,String sender,String receiver,long timeStamp) {
		super(sender, receiver, (T)song);
		this.song = song;
		this.transactionType = transactionType;
		this.timeStamp = super.getTimestamp();
	}
	
	public Song getSong() {
		return song;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public String getSender() {
		return sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public long getTimeStamp() {
		return timeStamp;
	}	
	
}
