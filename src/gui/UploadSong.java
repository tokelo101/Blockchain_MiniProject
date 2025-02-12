package gui;

import java.time.format.DateTimeFormatter;

import corelogic.Song;
import corelogic.users.Artist;
import corelogic.users.User;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * 
 * @author TM Monare 221022037
 *
 */
public class UploadSong extends GridPane{
	
	/**
	 * 
	 * @param content the Pane for uploading song
	 * @param primaryStage the primary stage
	 * @param artist the user that uploads song 
	 */
	public UploadSong(Pane content, Stage primaryStage, Artist artist) {
		
		GridPane frmUploadsong = new GridPane();
        frmUploadsong.setAlignment(Pos.CENTER);
        frmUploadsong.setHgap(10);
        frmUploadsong.setVgap(10);
        frmUploadsong.setPadding(new Insets(25));

        // Add form elements
        Label lbTitle = new Label("Upload Song");
        lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        frmUploadsong.add(lbTitle, 0, 0, 2, 1);
        GridPane.setHalignment(lbTitle, HPos.CENTER);

        Label lbSongTitle = new Label("Song Title:");
        frmUploadsong.add(lbSongTitle, 0, 1);

        TextField txtSongTitle = new TextField();
        frmUploadsong.add(txtSongTitle, 1, 1);
        
        Label lbComposer = new Label("Composer:" );
        frmUploadsong.add(lbComposer, 0, 2);

        TextField txtComposer = new TextField();
        frmUploadsong.add(txtComposer, 1, 2);
        

        Label lbLyricist = new Label("Lyricist:");
        frmUploadsong.add(lbLyricist, 0, 3);

        TextField txtLyricist = new TextField();
        frmUploadsong.add(txtLyricist, 1, 3);

        Label lbReleaseDate = new Label("Release Date:");
        frmUploadsong.add(lbReleaseDate, 0, 4);

        DatePicker dtReleaseDate = new DatePicker();
        frmUploadsong.add(dtReleaseDate, 1, 4);
        
        Label lbPublisher = new Label("Publisher:");
        frmUploadsong.add(lbPublisher, 0, 5);

        TextField txtPublisher = new TextField();
        frmUploadsong.add(txtPublisher, 1, 5);
        
        Label lbISRC = new Label("ISRC:");
        frmUploadsong.add(lbISRC, 0, 6);

        TextField txtISRC = new TextField();
        frmUploadsong.add(txtISRC, 1, 6);
        
        
        Button btnNext = new Button("next");
        btnNext.setPrefWidth(200);
        frmUploadsong.add(btnNext, 0, 7);
        GridPane.setHalignment(btnNext, HPos.CENTER);
        this.getChildren().add(frmUploadsong);
        
        btnNext.setOnAction(event->{
        	
        	try {
          	  // A listener for the selected date property
            	dtReleaseDate.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {

                    }
                });
            	
            	String isrc = txtISRC.getText();
        		String publisher = txtPublisher.getText();
        		String title = txtSongTitle.getText();
        		String releaseDate = dtReleaseDate.getValue().toString();
        		Song tempsong = new Song(isrc, publisher, title, releaseDate , null, null, artist);
        		tempsong.setArtist(artist.getName());
        		tempsong.setComposer(txtComposer.getText());
            	tempsong.setLyricist(txtLyricist.getText());
            	
            	
            	LicenseAndCopyrights licenseandcopyrights = new LicenseAndCopyrights(content, primaryStage,artist , tempsong);
            	this.getChildren().remove(0);
            	content.getChildren().remove(0);
            	content.getChildren().addAll(licenseandcopyrights);
        	}catch(NullPointerException npe) {
        		System.err.println("Date not Set ");
        	}

        });
	}
}
