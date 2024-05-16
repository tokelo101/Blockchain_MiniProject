package gui;

import corelogic.users.Artist;
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
import javafx.stage.Stage;

/**
 * 
 * @author TM Monare
 *
 */
public class Settings extends GridPane{
	/**
	 * 
	 * @param content the Pane for uploading song
	 * @param primaryStage the primary stage
	 * @param artist the user that uploads song 
	 */
	public Settings(Pane content, Stage primaryStage, Artist artist) {
		
		GridPane frmSettings = new GridPane();
        frmSettings.setAlignment(Pos.CENTER);
        frmSettings.setHgap(10);
        frmSettings.setVgap(10);
        frmSettings.setPadding(new Insets(25));

        // Add form elements
        Label lbTitle = new Label("Settings");
        lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        frmSettings.add(lbTitle, 0, 0, 2, 1);
        GridPane.setHalignment(lbTitle, HPos.CENTER);

        TextField txtchanageEmail = new TextField("Change Email");
        frmSettings.add(txtchanageEmail, 0, 1);

        Button btnEmail = new Button("Save Email");
        btnEmail.setPrefWidth(200);
        frmSettings.add(btnEmail, 1, 1);
        
        TextField txtchanagePassword = new TextField("old Password");
        frmSettings.add(txtchanagePassword, 1, 2);

        TextField txtnewPassword = new TextField("new Password");
        frmSettings.add(txtnewPassword,1 , 3);

        Button btnPassword = new Button("Save Password");
        btnPassword.setPrefWidth(200);
        frmSettings.add(btnPassword, 1, 4);
        
        Button btnSaveChanges = new Button("Save Changes");
        btnSaveChanges.setPrefWidth(200);
        frmSettings.add(btnSaveChanges, 1, 5);
        
        GridPane.setHalignment(btnSaveChanges, HPos.CENTER);
        this.getChildren().add(frmSettings);
        
        
        btnSaveChanges.setOnAction(event->{
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Settings");
            alert.setHeaderText("Changes Saved");
            alert.setContentText("Email and Passswrod saved successfuly keep them safe");
            alert.showAndWait();
        });
	}

}
