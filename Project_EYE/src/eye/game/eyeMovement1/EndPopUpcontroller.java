package eye.game.eyeMovement1;

import java.io.IOException;

import eye.main.Main;
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
	private AnchorPane SuccessPage, EndPopUpPage, zigzagPage;

	@FXML
	private Button Equal;

	@FXML
	private TextField answer;

	@FXML
	void answerEqual(ActionEvent event) throws IOException {
		String getAnswer = answer.getText();
		int ganswer = Integer.parseInt(getAnswer);

		if (Playcontroller.cnt == ganswer) {
//			zigzagPage = FXMLLoader.load(getClass().getResource("gameSuccess.fxml"));
//			EndPopUpPage.getChildren().setAll(zigzagPage);
			System.out.println(ganswer);
		} else {

			System.out.println(ganswer);

		}

	}

	public void ans() {

	}

	public void ans2() {

	}

}
