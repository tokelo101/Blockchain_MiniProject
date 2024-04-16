package gui;

import java.util.Date;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
	
	public UploadSong(Pane content, Stage primaryStage) {
		GridPane frmUploadsong = new GridPane();
        frmUploadsong.setAlignment(Pos.CENTER);
        frmUploadsong.setHgap(10);
        frmUploadsong.setVgap(10);
        frmUploadsong.setPadding(new Insets(25));

        // Add form elements
        Label lbTitle = new Label("Upload Song");
        lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        frmUploadsong.add(lbTitle, 0, 0, 2, 1);
        frmUploadsong.setHalignment(lbTitle, HPos.CENTER);

        Label lbSongTitle = new Label("Song Title:");
        frmUploadsong.add(lbSongTitle, 0, 1);

        TextField txtSongTitle = new TextField();
        frmUploadsong.add(txtSongTitle, 1, 1);
        
        Label lbArtist = new Label("Artist:");
        frmUploadsong.add(lbArtist, 0, 2);

        TextField txtArtist = new TextField();
        frmUploadsong.add(txtArtist, 1, 2);
        
        Label lbComposer = new Label("Composer:" );
        frmUploadsong.add(lbComposer, 0, 3);

        TextField txtComposer = new TextField();
        frmUploadsong.add(txtComposer, 1, 3);
        

        Label lbLyricist = new Label("Lyricist:");
        frmUploadsong.add(lbLyricist, 0, 4);

        TextField txtLyricist = new TextField();
        frmUploadsong.add(txtLyricist, 1, 4);

        Label lbReleaseDate = new Label("Release Date:");
        frmUploadsong.add(lbReleaseDate, 0, 5);

        DatePicker dtReleaseDate = new DatePicker();
        frmUploadsong.add(dtReleaseDate, 1, 5);

        Label lbPublisher = new Label("Publisher:");
        frmUploadsong.add(lbPublisher, 0, 6);

        TextField txtPublisher = new TextField();
        frmUploadsong.add(txtPublisher, 1, 6);
        
        Label lbISRC = new Label("ISRC:");
        frmUploadsong.add(lbISRC, 0, 7);

        TextField txtISRC = new TextField();
        frmUploadsong.add(txtISRC, 1, 7);
        
        Button btnNext = new Button("next");
        btnNext.setPrefWidth(200);
        frmUploadsong.add(btnNext, 0, 8);
        frmUploadsong.setHalignment(btnNext, HPos.CENTER);
        this.getChildren().add(frmUploadsong);
        
        btnNext.setOnAction(event->{
        	LicenseAndCopyrights licenseandcopyrights = new LicenseAndCopyrights(content, primaryStage);
        	this.getChildren().remove(0);
        	content.getChildren().remove(0);
        	content.getChildren().addAll(licenseandcopyrights);
        	//this.getChildren().add(mainBox);
        });
	}
}
