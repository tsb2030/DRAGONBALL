package eye.game.eyeMovement2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class controller {
	static int duration = 4;
	@FXML
	private AnchorPane acpane;

	@FXML
	private Button go2;

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

		try {
			Parent root = FXMLLoader.load(getClass().getResource("design2.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) acpane.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
