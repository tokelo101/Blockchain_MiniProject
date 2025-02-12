
package corelogic;
import java.io.File;
import java.util.List;
import acsse.csc03a3.Transaction;
import corelogic.users.Artist;

/**
 * 
 * @author TM Monare 221022037
 *
 */
public class Song{

	private String songTitle;
	private String artist;
	private String composer;
	private String lyricist;
	private String releaseDate;
	private String publisher;
	private String ISRC; //International Standard Recording Code
	private String copyrightsHolder;
	private String copyrightRegNumber;
	private File licenseTerms;
	private File lyrics;
	
	
	//Licence prices 
	private double copyRights_price;
	private double syncronizationRights_price;
	private double performanceRights_price;
	private double mechanicalRights_price;
	private double mastersRights_price;
	
	//for a transaction
	private String ArtistAddress;
	private List<Transaction<Song>> transactions;
    
	public Song(String ISRC,String publisher,String songTitle, String releaseDate,String copyrightsHolder,String copyrightRegNumber, Artist artist ) {        
        
		this.ISRC = ISRC;
		this.publisher = publisher;
		this.songTitle = songTitle;
		this.releaseDate = releaseDate;
		this.copyrightsHolder = copyrightsHolder;
		this.copyrightRegNumber = copyrightRegNumber;
		
			this.ArtistAddress = artist.getPublicKey();
		
    }
	
	public String getSongTitle() {
		return songTitle;
	}


	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public String getComposer() {
		return composer;
	}


	public void setComposer(String composer) {
		this.composer = composer;
	}


	public String getLyricist() {
		return lyricist;
	}


	public void setLyricist(String lyricist) {
		this.lyricist = lyricist;
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

	public String getArtistAddress() {
		return ArtistAddress;
	}
	
	public void setArtistAddress(String ArtistAddress) {
		this.ArtistAddress = ArtistAddress;
	}
	
	//prices Setters and Getters
	public File getLicenseTerms() {
		return licenseTerms;
	}

	public void setLicenseTerms(File licenseTerms) {
		this.licenseTerms = licenseTerms;
	}

	public double getCopyRights_price() {
		return copyRights_price;
	}

	public void setCopyRights_price(double copyRights_price) {
		this.copyRights_price = copyRights_price;
	}

	public double getSyncronizationRights_price() {
		return syncronizationRights_price;
	}

	public void setSyncronizationRights_price(double syncronizationRights_price) {
		this.syncronizationRights_price = syncronizationRights_price;
	}

	public double getPerfomanceRights_price() {
		return performanceRights_price;
	}

	public void setPerformanceRights_price(double perfomanceRights_price) {
		this.performanceRights_price = perfomanceRights_price;
	}

	public double getMechanicalRights_price() {
		return mechanicalRights_price;
	}

	public void setMechanicalRights_price(double mechanicalRights_price) {
		this.mechanicalRights_price = mechanicalRights_price;
	}

	public double getMastersRights_price() {
		return mastersRights_price;
	}

	public void setMastersRights_price(double mastersRights_price) {
		this.mastersRights_price = mastersRights_price;
	}

	public void setTransactions(List<Transaction<Song>> transactions) {
		this.transactions = transactions;
	}

}
