package gui;

import java.io.File;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	
	public LicenseAndCopyrights(Pane content, Stage primaryStage) {
		GridPane frmUploadsong = new GridPane();
        frmUploadsong.setAlignment(Pos.CENTER);
        frmUploadsong.setHgap(10);
        frmUploadsong.setVgap(10);
        frmUploadsong.setPadding(new Insets(25));

        // Add form elements
        Label lbTitle = new Label("Copyrights Information");
        lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        frmUploadsong.add(lbTitle, 0, 0, 2, 1);
        frmUploadsong.setHalignment(lbTitle, HPos.CENTER);

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
        
        
        Button btnBack = new Button("Back");
        frmUploadsong.add(btnBack, 0, 5);
        
        Button btnUpload = new Button("Upload");
        btnUpload.setPrefWidth(200);
        frmUploadsong.add(btnUpload, 1, 5);
        frmUploadsong.setHalignment(btnUpload, HPos.CENTER);
        this.getChildren().add(frmUploadsong);
        
        btnBack.setOnAction(event->{
        	UploadSong uploadsong = new UploadSong(content, primaryStage);
        	this.getChildren().remove(0);
        	content.getChildren().remove(0);
        	content.getChildren().addAll(uploadsong);
        });
        
        btnLicensingInfo.setOnAction(event->{
        	FileChooser fc = new FileChooser();
        	fc.setInitialDirectory(new File(System.getProperty("user.dir")));
            File file = fc.showOpenDialog(primaryStage);
           
            if (file != null) {
                // Handle the selected file
                System.out.println("Selected File: " + file.getAbsolutePath());
            }
        });
        
        btnUpload.setOnAction(event->{
        	//Create a Transaction and a Block for the Blockchain 
        });
	}
}
