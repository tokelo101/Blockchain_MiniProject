import java.io.File;
import java.util.List;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;

public class Song<T> extends Block<T>{
	
	private String songTitle;
	private String artists[];
	private String composers[];
	private String lyricists[];
	private String releaseDate;
	private String publisher;
	private String ISRC; //International Standard Recording Code
	private String copyrightsHolder;
	private String copyrightRegNumber;
	private File licesnseAndTerms;
	private File lyrics;
	private String previousHash;
	private List<Transaction<T>> transactions;
	
	
	public Song(String previousHash,List<Transaction<T>> transactions) {
        super(previousHash, transactions);
        
        this.previousHash = previousHash;
        this.transactions = transactions;
        
        System.out.println("New Song Created");
    }
	
	public String getSongTitle() {
		return songTitle;
	}


	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}


	public String[] getArtists() {
		return artists;
	}


	public void setArtists(String[] artists) {
		this.artists = artists;
	}


	public String[] getComposers() {
		return composers;
	}


	public void setComposers(String[] composers) {
		this.composers = composers;
	}


	public String[] getLyricists() {
		return lyricists;
	}


	public void setLyricists(String[] lyricists) {
		this.lyricists = lyricists;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getISRC() {
		return ISRC;
	}


	public void setISRC(String iSRC) {
		ISRC = iSRC;
	}


	public String getCopyrightsHolder() {
		return copyrightsHolder;
	}


	public void setCopyrightsHolder(String copyrightsHolder) {
		this.copyrightsHolder = copyrightsHolder;
	}


	public String getCopyrightRegNumber() {
		return copyrightRegNumber;
	}


	public void setCopyrightRegNumber(String copyrightRegNumber) {
		this.copyrightRegNumber = copyrightRegNumber;
	}


	public File getLicesnseAndTerms() {
		return licesnseAndTerms;
	}


	public void setLicesnseAndTerms(File licesnseAndTerms) {
		this.licesnseAndTerms = licesnseAndTerms;
	}


	public File getLyrics() {
		return lyrics;
	}


	public void setLyrics(File lyrics) {
		this.lyrics = lyrics;
	}


	public String getPreviousHash() {
		return previousHash;
	}


	public List getTransactions() {
		return transactions;
	}


	
    
}
