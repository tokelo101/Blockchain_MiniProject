package gui;

import java.util.ArrayList;

import corelogic.*;
import javafx.collections.*;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class SongsView extends StackPane{
	
	private HBox container;
	private ListView<Label> songList = new ListView<>();
	private GridPane songDetails;
	private TextField txtName;
	private TextField txtSurname;
	private TextField txtEmail;
	private PasswordField password;
	private Artist artist;
	
	public SongsView(Pane content, Stage primaryStage, User user) {
	    container = new HBox();
	    songDetails = new GridPane();

	    // Reading Songs from File
	    ObservableList<Label> songs = FXCollections.observableArrayList();

	    Label lbSongList;
	    
	    if(user.getUserType().toLowerCase().equals("artist")) {
	    	 // Add items to the ObservableList
		    lbSongList = new Label("Artist Song List");
		    lbSongList.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		    songs.add(lbSongList);
		    artist = (Artist)user;
		    ArrayList<Song> artistsongs = artist.GetAllArtistSongs();
		    for(Song s: artistsongs){
		    	Label songlabel = new Label(s.getISRC());
		    	songs.add(songlabel);
		    }
	    }else {
	    	 // Add items to the ObservableList
		    lbSongList = new Label("All Songs");
		    lbSongList.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		    songs.add(lbSongList);
		    
		}
	    
	   

	    // Song Details

	    Label lbTitle = new Label("Song Details");
	    lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	    songDetails.add(lbTitle, 0, 0, 2, 1);
	    GridPane.setHalignment(lbTitle, HPos.CENTER);

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
	    songList = new ListView<>(songs);

	    // Add listener to handle song selection
	    songList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	        	
	        	Song song = artist.GetSong(newValue.getText());
	            lbSongTitle_value.setText(song.getSongTitle());
	            lbComposer_value.setText(song.getComposer());
	            lbLyricist_value.setText(song.getLyricist());
	            lbReleaseDate_value.setText(song.getReleaseDate());
	            lbPublisher_value.setText(song.getPublisher());
	            lbISRC_value.setText(song.getISRC());
	        }
	    });
	    
	    //remove the previous contents
	    //content.getChildren().remove(0);
		//clean content
		//container.getChildren().clear();
	    container.getChildren().addAll(songList, songDetails);
	    this.getChildren().addAll(container);
	}
}
