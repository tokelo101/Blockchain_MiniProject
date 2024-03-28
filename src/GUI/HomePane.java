package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomePane extends StackPane {
    private VBox mainBox;
    private HBox searchBox;
    private TextField txtSearchBox;
    private Button btnSearchButton;
    private Button btnRegister;
    private RegisterPane register;
    public HomePane(){
    	btnRegister = new Button("Register");
        mainBox = new VBox();
        
        mainBox.setAlignment(Pos.TOP_CENTER);
        searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER);
        txtSearchBox = new TextField();
        txtSearchBox.setPromptText("search");
        btnSearchButton = new Button("Serach Icon");
        searchBox.getChildren().addAll(txtSearchBox, btnSearchButton);
        mainBox.getChildren().addAll(searchBox, btnRegister);
        this.getChildren().add(mainBox);
        
        
        btnRegister.setOnAction(event->{
        	register = new RegisterPane();
        	this.getChildren().remove(0);
        	mainBox.getChildren().addAll(register);
        	this.getChildren().add(mainBox);
        });
    }
	
	
}

