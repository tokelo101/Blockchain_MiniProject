package GUI;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RegisterPane extends StackPane {
	
	public RegisterPane() {

		 GridPane gridPane = new GridPane();
	        gridPane.setAlignment(Pos.CENTER);
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        gridPane.setPadding(new Insets(25));

	        // Add form elements
	        Label lbTitle = new Label("Register Form");
	        lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	        gridPane.add(lbTitle, 0, 0, 2, 1);
	        GridPane.setHalignment(lbTitle, HPos.CENTER);

	        Label lbStagename = new Label("Stagename:");
	        gridPane.add(lbStagename, 0, 1);

	        TextField txtStagename = new TextField();
	        gridPane.add(txtStagename, 1, 1);
	        
	        Label lbName = new Label("Name:");
	        gridPane.add(lbName, 0, 2);

	        TextField txtName = new TextField();
	        gridPane.add(txtName, 1, 2);
	        
	        Label lbSurname = new Label("Surname:");
	        gridPane.add(lbSurname, 0, 3);

	        TextField txtSurname = new TextField();
	        gridPane.add(txtSurname, 1, 3);

	        Label lbEmail = new Label("Email:");
	        gridPane.add(lbEmail, 0, 4);

	        TextField txtEmail = new TextField();
	        gridPane.add(txtEmail, 1, 4);

	        Label lbPassword = new Label("Password:");
	        gridPane.add(lbPassword, 0, 5);

	        PasswordField password = new PasswordField();
	        gridPane.add(password, 1, 5);

	        Button btnRegister = new Button("Register");
	        gridPane.add(btnRegister, 0, 6, 2, 1);
	        GridPane.setHalignment(btnRegister, HPos.CENTER);
	        this.getChildren().add(gridPane);
	}
	
}
