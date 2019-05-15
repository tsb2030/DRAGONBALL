package eye.game.eyeMovement1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.game.eyeMovement1.Playcontroller;
import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EndPopUpcontroller  {

	@FXML
	private AnchorPane SuccessPage, EndPopUpPage, stripPage;

	@FXML
	private Button Equal;

	@FXML
	private TextField answer;

	@FXML
	void answerEqual(ActionEvent event) {
		String getAnswer = answer.getText();
		int ganswer = Integer.parseInt(getAnswer);
		if (Playcontroller.cnt == ganswer) {
			try {
				stripPage = FXMLLoader.load(getClass().getResource("gameSuccess.fxml"));
				EndPopUpPage.getChildren().setAll(stripPage);
				System.out.println(ganswer);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				stripPage = FXMLLoader.load(getClass().getResource("gameFail.fxml"));
				EndPopUpPage.getChildren().setAll(stripPage);
				System.out.println(ganswer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void ans() {

	}

	public void ans2() {

	}


}
