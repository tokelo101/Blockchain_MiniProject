package gui;

import java.io.File;

import acsse.csc03a3.Transaction;
import corelogic.Song;
import corelogic.users.Artist;
import corelogic.users.User;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LicenseAndCopyrights extends GridPane{
	private File licenceTerms;
	
	public LicenseAndCopyrights(Pane content, Stage primaryStage,Artist artist, Song song) {
		GridPane frmUploadsong = new GridPane();
        frmUploadsong.setAlignment(Pos.CENTER);
        frmUploadsong.setHgap(10);
        frmUploadsong.setVgap(10);
        frmUploadsong.setPadding(new Insets(25));
     
        // Add form elements
        Label lbTitle = new Label("Copyrights Information");
        lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        frmUploadsong.add(lbTitle, 0, 0, 2, 1);
        GridPane.setHalignment(lbTitle, HPos.CENTER);

        Label lbCopyrightHolder = new Label("Copyright Holder:");
        frmUploadsong.add(lbCopyrightHolder, 0, 1);

        TextField txtCopyrightHolder = new TextField();
        frmUploadsong.add(txtCopyrightHolder, 1, 1);
        
        Label lbCopyrightReg = new Label("Copyright Reg:");
        frmUploadsong.add(lbCopyrightReg, 0, 2);

        TextField txtCopyrightReg = new TextField();
        frmUploadsong.add(txtCopyrightReg, 1, 2);
        
        Label lbLicensingInfo = new Label("Licensing Information:" );
        frmUploadsong.add(lbLicensingInfo, 0, 3);
        
        Button btnLicensingInfo = new Button("Upload License Information");
        btnLicensingInfo.setPrefWidth(200);
        frmUploadsong.add(btnLicensingInfo, 1, 3);
        
        Label lbCopyRights = new Label("CopyRights Price: ");
        frmUploadsong.add(lbCopyRights, 0, 4);

        TextField txtCopyRights = new TextField();
        txtCopyRights.setPromptText("CopyRights Price");
        frmUploadsong.add(txtCopyRights, 1, 4);        

        Label lbSyncronizationRights = new Label("SyncronizationRights Price: ");
        frmUploadsong.add(lbSyncronizationRights, 0, 5);

        TextField txtSyncronizationRights = new TextField();
        txtSyncronizationRights.setPromptText("SyncronizationRights Price");
        frmUploadsong.add(txtSyncronizationRights, 1, 5);

        Label lbPerformanceRights = new Label("PerformanceRights Price: ");
        frmUploadsong.add(lbPerformanceRights, 0, 6);

        TextField txtPerformanceRights = new TextField();
        txtPerformanceRights.setPromptText("PerformanceRights Price");
        frmUploadsong.add(txtPerformanceRights, 1, 6);

        Label lbMechanicalRights = new Label("MechanicalRights Price: ");
        frmUploadsong.add(lbMechanicalRights, 0, 7);

        TextField txtMechanicalRights = new TextField();
        txtMechanicalRights.setPromptText("MechanicalRights Price");
        frmUploadsong.add(txtMechanicalRights, 1, 7);

        Label lbMastersRights = new Label("MastersRights Price: ");
        frmUploadsong.add(lbMastersRights, 0, 8);

        TextField txtMastersRights = new TextField();
        txtMastersRights.setPromptText("MastersRights Price");
        frmUploadsong.add(txtMastersRights, 1, 8);
        
        
        
        Button btnBack = new Button("Back");
        frmUploadsong.add(btnBack, 0, 10);
        
        Button btnUpload = new Button("Upload Song");
        btnUpload.setPrefWidth(200);
        frmUploadsong.add(btnUpload, 1, 10);
        GridPane.setHalignment(btnUpload, HPos.CENTER);
        this.getChildren().add(frmUploadsong);
        
        btnBack.setOnAction(event->{
        	UploadSong uploadsong = new UploadSong(content, primaryStage, artist);
        	this.getChildren().remove(0);
        	content.getChildren().remove(0);
        	content.getChildren().addAll(uploadsong);
        });
        
        btnLicensingInfo.setOnAction(event->{
        	FileChooser fc = new FileChooser();
        	fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        	licenceTerms = fc.showOpenDialog(primaryStage);
          
        });
        
        btnUpload.setOnAction(event->{
        	//Create a Transaction and a Block for the Blockchain 
        	
        	String isrc = song.getISRC();
    		String publisher = song.getPublisher();
    		String title = song.getSongTitle();
    		String releaseDate = song.getReleaseDate();
    		String artistname = song.getArtist();
    		String composer = song.getComposer();
    		String lyricist = song.getLyricist();
    		//next page
    		String copyrightHolder = txtCopyrightHolder.getText();
    		String copyrightRegNo = txtCopyrightReg.getText();
    		File licenceTerms_ = licenceTerms;
        	
        	Song newsong = new Song(isrc, publisher, title, releaseDate, copyrightHolder, copyrightRegNo, artist);
        	newsong.setArtist(artistname);
    		newsong.setComposer(composer);
        	newsong.setLyricist(lyricist);
        	newsong.setLicesnseAndTerms(licenceTerms_);

        	newsong.setCopyRights_price(Integer.parseInt(txtCopyRights.getText()));
        	newsong.setSyncronizationRights_price(Integer.parseInt(txtSyncronizationRights.getText()));
        	newsong.setPerformanceRights_price(Integer.parseInt(txtPerformanceRights.getText()));
        	newsong.setMechanicalRights_price(Integer.parseInt(txtMechanicalRights.getText()));
        	newsong.setMastersRights_price(Integer.parseInt(txtMastersRights.getText()));
            	
        		//put song to song list
            	if(artist.UploadSong(newsong)) {
            		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Song Upload");
                    alert.setHeaderText("Song Uploaded Sucessfully.");
                    alert.setContentText(" The Song will be send to 5 peers for validation");
                    alert.showAndWait();
            	}else{
            		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Song Upload");
                    alert.setHeaderText("Song Upload Failed");
                    alert.setContentText(" please try again");
                    alert.showAndWait();
            	}
        	//get the latest transaction file from peers
        	
        	//add transaction to the file
        	
        	//if transactions are more than five
        	
        	//Create Block
        	
        	//send block for validation
        	
        	//Add block to Blockchain 
        	
        	//send updated Blockchain to peers
        	
        	
        	
        });
	}
}
