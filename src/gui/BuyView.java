package gui;

import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

public class BuyView extends StackPane{
	
	public BuyView() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Purchase Transaction");
        alert.setHeaderText("Transaction Successful");
        alert.setContentText("An Email will be send to you Shortly");
        alert.showAndWait();
	}
}
