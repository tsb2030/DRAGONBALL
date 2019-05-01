package mis;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class controller {
	static int duration = 4;
	@FXML
	private AnchorPane acpane;


    @FXML
    void first(MouseEvent event) {
    	duration = 6;

    }


    @FXML
    void second(MouseEvent event) {
    	duration = 4;
    }

    @FXML
    void third(MouseEvent event) {
    	duration = 2;

    }

	@FXML
	private void go2(MouseEvent ev) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("design2.fxml"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();
		acpane.getScene().getWindow().hide();

	}

}
