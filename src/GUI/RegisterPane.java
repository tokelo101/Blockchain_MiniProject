package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class RegisterPane extends StackPane {
	private VBox frmRegister;
	private HBox frmDetails;
	private Label lbRegister;
	private Label lbName;
	private Label lbSurname;
	private Label lbEmail;
	private Label lbPassword;
	private Label lbConfirmPassword;
	private Button btnRegister;
	

	
	public RegisterPane() {
		frmRegister = new VBox();
		frmDetails = new HBox();
		
		lbRegister = new Label("Register");
		lbName = new Label("Name");
		lbSurname = new Label("Surname");
		lbEmail = new Label("Email");
		lbPassword = new Label("Password");
		lbConfirmPassword = new Label("Confirm Password");
		btnRegister = new Button("Register");
		
		frmDetails.getChildren().addAll(lbName, lbSurname);
		frmRegister.getChildren().addAll(lbRegister, frmDetails, btnRegister);
		
		frmDetails.setAlignment(Pos.CENTER);
		frmRegister.setAlignment(Pos.CENTER);
		this.getChildren().add(frmRegister);
	}
	
}
