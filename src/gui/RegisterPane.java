package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import corelogic.*;

public class RegisterPane extends StackPane {
	
	private UserHandler userhandler;
	private ComboBox<String> cmbUserType;
	private TextField txtName;
	private TextField txtSurname;
	private TextField txtEmail;
	private PasswordField password;
	private Button btnRegister = null;
	private  ObservableList<String> userTypes = FXCollections.observableArrayList(
            "Artist",
            "Record label",
            "Publisher",
            "Distributor"
    );
	public RegisterPane(Pane content, Stage primaryStage) {
		userhandler = new UserHandler();
		
		 GridPane gridPane = new GridPane();
	        gridPane.setAlignment(Pos.CENTER);
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        gridPane.setPadding(new Insets(25));

	        // Add form elements
	        Label lbTitle = new Label("Register User");
	        lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	        gridPane.add(lbTitle, 0, 0, 2, 1);
	        GridPane.setHalignment(lbTitle, HPos.CENTER);

	        Label lbUserType = new Label("User Type:");
	        gridPane.add(lbUserType, 0, 1);

	        cmbUserType = new ComboBox<>(userTypes);
	        cmbUserType.setValue("Artist");
	        gridPane.add(cmbUserType, 1, 1);
	        
	        Label lbName = new Label("Name:");
	        gridPane.add(lbName, 0, 2);

	        txtName = new TextField();
	        gridPane.add(txtName, 1, 2);
	        
	        Label lbSurname = new Label("Surname:");
	        gridPane.add(lbSurname, 0, 3);

	        txtSurname = new TextField();
	        gridPane.add(txtSurname, 1, 3);

	        Label lbEmail = new Label("Email:");
	        gridPane.add(lbEmail, 0, 4);

	        txtEmail = new TextField();
	        gridPane.add(txtEmail, 1, 4);

	        Label lbPassword = new Label("Password:");
	        gridPane.add(lbPassword, 0, 5);

	        password = new PasswordField();
	        gridPane.add(password, 1, 5);

	        btnRegister = new Button("Register");
	        gridPane.add(btnRegister, 0, 6, 2, 1);
	        GridPane.setHalignment(btnRegister, HPos.CENTER);
	        this.getChildren().add(gridPane);
	        
	        btnRegister.setOnAction(event->{
	        	//create User
	        	String usertype = cmbUserType.getValue();
	        	String name = txtName.getText();
	        	String surname = txtSurname.getText();
	        	String email = txtEmail.getText();
	        	String _password = password.getText();  //TODO: Encrypt the password
	        	
	        	User newuser = new User(usertype, name, surname, email, _password);
	        	//use userhandler to save user to file
	        	boolean registered = userhandler.RegisterNewUser(newuser);
	        	
	        	if(registered) {
	        		newuser.PrintUser();
	        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("User Registration");
	                alert.setHeaderText("Welcome " +newuser.getName());
	                alert.setContentText("Your Public Key is: " + newuser.getPublicKey());
	                alert.showAndWait();
	                
	                HomePane home = new HomePane(primaryStage, newuser);
	            	this.getChildren().remove(0);
	            	content.getChildren().remove(0);
	            	content.getChildren().addAll(home);
	            	
	                
	        	}else {
	        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("User Registration");
	                alert.setHeaderText("Registration was Unsuccessful");
	                alert.setContentText("please try to register again");
	                alert.showAndWait();
	        	}
	        	
	        });
	}
	
	
	
}
