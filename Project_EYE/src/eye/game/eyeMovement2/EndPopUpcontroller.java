package eye.game.eyeMovement2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EndPopUpcontroller {

	@FXML
	private AnchorPane acpane;

	@FXML
	private Button Equal;

	@FXML
	private TextField answer;

	@FXML
	void answerEqual(ActionEvent event) {
		String getAnswer = answer.getText();
		int ganswer = Integer.parseInt(getAnswer);

		if (Playcontroller.cnt == ganswer) {
			System.out.println(ganswer);
		} else {
			System.out.println(Playcontroller.cnt);

		}

	}

	public void ans() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("gameSuccess.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) acpane.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void ans2() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("gameFail.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) acpane.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
