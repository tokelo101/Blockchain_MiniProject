package GUI;

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

    public HomePane(){
        mainBox = new VBox();
        searchBox = new HBox();
        txtSearchBox = new TextField();
        txtSearchBox.setPromptText("search");
        btnSearchButton = new Button("Serach Icon");
        searchBox.getChildren().addAll(txtSearchBox, btnSearchButton);
        mainBox.getChildren().add(searchBox);
        this.getChildren().addAll(mainBox);
    }
}
