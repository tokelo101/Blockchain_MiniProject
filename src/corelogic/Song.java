
package corelogic;
import java.io.File;
import java.util.List;
import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;

/**
 * 
 * @author TM Monare 221022037
 *
 */
public class Song{

	private String songTitle;
	private String artists[];
	private String composers[];
	private String lyricists[];
	private String releaseDate;
	private String publisher;
	private String ISRC; //International Standard Recording Code
	private String copyrightsHolder;
	private String copyrightRegNumber;
	private File licenseTerms;
	private File lyrics;
	private List<Transaction<Song>> transactions;
    
	public Song(String ISRC,String publisher,String songTitle, String releaseDate,String copyrightsHolder,String copyrightRegNumber ) {        
        
		this.ISRC = ISRC;
		this.publisher = publisher;
		this.songTitle = songTitle;
		this.releaseDate = releaseDate;
		this.copyrightsHolder = copyrightsHolder;
		this.copyrightRegNumber = copyrightRegNumber;
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
		return licenseTerms;
	}


	public void setLicesnseAndTerms(File licesnseAndTerms) {
		this.licenseTerms = licesnseAndTerms;
	}


	public File getLyrics() {
		return lyrics;
	}


	public void setLyrics(File lyrics) {
		this.lyrics = lyrics;
	}


	public List<Transaction<Song>> getTransactions() {
		return transactions;
	}
	
	public Transaction<Song> addTransaction(Transaction<Song> transaction) {
		transactions.add(transaction);
		return transaction;
	}


	
    
}
