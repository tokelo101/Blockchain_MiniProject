package corelogic;

import java.util.ArrayList;

public class Distributor extends User{
	private double availableFunds;
	private SongHandler songhandler;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Distributor(String usertype, String name, String surname, String email, String password) {
		super(usertype, name, surname, email, password);
		songhandler = new SongHandler(this);
	}
	
	public double getAvailableFunds() {
		return availableFunds;
	}


	/**
	 * 
	 * @param ISRC International Standard Recording Code is a Unique Identifier of a song
	 * @return song the song to be returned
	 */
	public Song GetSong(String ISRC){
		return songhandler.ReadSong(ISRC);
	}
	
	
	public ArrayList<Song> GetAllSongs(){
		return songhandler.ReadAllSongs();
	}
}
