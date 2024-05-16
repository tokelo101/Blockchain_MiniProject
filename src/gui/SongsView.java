package gui;

import java.util.ArrayList;

import corelogic.*;
import corelogic.users.Artist;
import corelogic.users.Distributor;
import corelogic.users.User;
import javafx.collections.*;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class SongsView<K,V> extends StackPane{
	
	private HBox container;
	private ListView<SongEntry<K,V>> songList = new ListView<>();
	private GridPane songDetails;
	private Artist artist;
	private Distributor distributor;
	
	private static Song selectedSong;
	
	/**
	 * 
	 * @param content the Pane for uploading song
	 * @param primaryStage the primary stage
	 * @param artist the user that uploads song 
	 */
	@SuppressWarnings("unchecked")
	public SongsView(Pane content, Stage primaryStage, User user) {
	    container = new HBox();
	    songDetails = new GridPane();

	    // Reading Songs from File
	    ObservableList<SongEntry<K,V>> songs = FXCollections.observableArrayList();

	    Label lbSongList;
	    
	    if(user.getUserType().equals("Artist")) {
	    	 // Add items to the ObservableList
		    lbSongList = new Label("Artist Song List");
		    lbSongList.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		    
		    artist = new Artist(user.getUserType(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
		    artist.setPUBLIC_KEY(user.getPublicKey());
		    artist.setPRIVATE_KEY(user.getPrivateKey());
		    ArrayList<Song> artistsongs = artist.GetAllArtistSongs();
		    
		    
		    for(Song s: artistsongs){
		    	SongEntry<String,String> songEntry = new SongEntry<String, String>(s.getISRC(), s.getSongTitle());
		    	songs.add((SongEntry<K, V>) songEntry);
		    }
	    }else {
	    	 // Add items to the ObservableList
		    lbSongList = new Label("All Songs");
		    lbSongList.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		    distributor  = new Distributor(user.getUserType(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
		    distributor.setPUBLIC_KEY(user.getPublicKey());
		    distributor.setPRIVATE_KEY(user.getPrivateKey());
		    
		    
		    ArrayList<Song> artistsongs = distributor.GetAllSongs();
		    
		    
		    for(Song s: artistsongs){
		    	SongEntry<String,String> songEntry = new SongEntry<String, String>(s.getISRC(), s.getSongTitle());
		    	songs.add((SongEntry<K, V>) songEntry);
		    }
		}
	    
	   

	    // Song Details

	    Label lbTitle = new Label("Song Details");
	    

	    Label lbSongTitle = new Label("Song Title:");
	    songDetails.add(lbSongTitle, 0, 1);

	    Label lbSongTitle_value = new Label("");
	    songDetails.add(lbSongTitle_value, 1, 1);

	    Label lbComposer = new Label("Composer:");
	    songDetails.add(lbComposer, 0, 2);

	    Label lbComposer_value = new Label("");
	    songDetails.add(lbComposer_value, 1, 2);

	    Label lbLyricist = new Label("Lyricist:");
	    songDetails.add(lbLyricist, 0, 3);

	    Label lbLyricist_value = new Label("");
	    songDetails.add(lbLyricist_value, 1, 3);

	    Label lbReleaseDate = new Label("Release Date:");
	    songDetails.add(lbReleaseDate, 0, 4);

	    Label lbReleaseDate_value = new Label("");
	    songDetails.add(lbReleaseDate_value, 1, 4);

	    Label lbPublisher = new Label("Publisher:");
	    songDetails.add(lbPublisher, 0, 5);

	    Label lbPublisher_value = new Label("");
	    songDetails.add(lbPublisher_value, 1, 5);

	    Label lbISRC = new Label("ISRC:");
	    songDetails.add(lbISRC, 0, 6);

	    Label lbISRC_value = new Label("");
	    songDetails.add(lbISRC_value, 1, 6);

	    // Put songs on the list view
	    songList = new ListView<SongEntry<K,V>>(songs);

	    // Add listener to handle song selection
	    songList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	        	
	        	Song song;
	        	if(artist!=null) {
	        		song = artist.GetSong((String)newValue.getKey());	
	        		selectedSong = song;
	        		lbSongTitle_value.setText(song.getSongTitle());
		            lbComposer_value.setText(song.getComposer());
		            lbLyricist_value.setText(song.getLyricist());
		            lbReleaseDate_value.setText(song.getReleaseDate());
		            lbPublisher_value.setText(song.getPublisher());
		            lbISRC_value.setText(song.getISRC());
	        	}
	        	if(distributor!=null) {
	        		song = distributor.GetSong((String)newValue.getKey());
	        		selectedSong = song;
	        		lbSongTitle_value.setText(song.getSongTitle());
		            lbComposer_value.setText(song.getComposer());
		            lbLyricist_value.setText(song.getLyricist());
		            lbReleaseDate_value.setText(song.getReleaseDate());
		            lbPublisher_value.setText(song.getPublisher());
		            lbISRC_value.setText(song.getISRC());
	        	}
	        	
	        	

	        }
	    });
	    
	    
	    songList.setCellFactory(param -> new ListCell<SongEntry<K,V>>() {
            @Override
            protected void updateItem(SongEntry<K,V> item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText((String)item.getValue());
                    getStyleClass().add("list-cell");
                }
            }
        });
	    
	    VBox songview = new VBox();
	    this.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
	    
	    lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	    songDetails.add(lbTitle, 0, 0, 2, 1);
	    GridPane.setHalignment(lbTitle, HPos.CENTER);
	    
	    lbSongList.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	    
	    songview.getChildren().addAll(lbSongList,songList);
	    container.getChildren().addAll(songview, songDetails);
	    this.getChildren().addAll(container);
	}
	
	/**
	 * 
	 * @return song
	 */
	public static Song selectedSong() {
		return selectedSong;
	};
}
