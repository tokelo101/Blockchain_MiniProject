package corelogic.users;

import java.io.File;
import java.util.ArrayList;

import corelogic.Song;
import corelogic.SongHandler;

/**
 * 
 * @author TM Monare 221022037
 *
 */
public class Artist extends User{
	
	private SongHandler songhandler;
	
	public Artist(String usertype, String name, String surname, String email, String password) {
		super(usertype, name, surname, email, password);
		songhandler = new SongHandler(this);
	}
	
	/**
	 * 
	 * @param <T>
	 * @param song a song object to be written to the block
	 * @return status returns a status on whether the file has been written or not
	 */
	public <T> boolean UploadSong(Song song) {
		return songhandler.WriteSong(song);
	}

	/**
	 * 
	 * @param ISRC International Standard Recording Code is a Unique Identifier of a song
	 * @return song the song to be returned
	 */
	public Song GetSong(String ISRC){
		return songhandler.ReadSong(ISRC);
	}
	
	
	public ArrayList<Song> GetAllArtistSongs(){
		return songhandler.readArtistSongs(getPublicKey());
	}
	
	
	/**
	 * 
	 * @param <T>
	 * @param ISRC International Standard Recording Code, Unique Song Identifier
	 * @return return status based on success or failure
	 */
	public <T> boolean UpdateLicenceTerms(String ISRC, File licenceTerms) {
		
		boolean status = false;
		
		//get a song from a file
		
		Song song = GetSong(ISRC);
		if(song==null) {
			return false;
		}
		
		String isrc = song.getISRC();
		String publisher = song.getPublisher();
		String title = song.getSongTitle();
		String releaseDate = song.getReleaseDate();
		String copyrightHolder = song.getCopyrightsHolder();
		String copyrightRegNo = song.getCopyrightRegNumber();
		
		
		return status;
	}
}
