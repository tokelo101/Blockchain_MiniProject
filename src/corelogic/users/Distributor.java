package corelogic.users;

import java.util.ArrayList;

import corelogic.Song;
import corelogic.SongHandler;

public class Distributor extends User{
	
	private SongHandler songhandler;

	public Distributor(String usertype, String name, String surname, String email, String password) {
		super(usertype, name, surname, email, password);
		songhandler = new SongHandler(this);
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
