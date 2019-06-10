package eye.game.eyeMovement2;

import java.io.IOException;

import eye.Music;
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

	// 색변환 정답 확인 메소드
	@FXML
	void answerEqual(ActionEvent event) {
		String getAnswer = answer.getText();
		int ganswer = Integer.parseInt(getAnswer);

		if (Playcontroller.cnt == ganswer) {
			try {
				Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
				effectMusic.start();
				zigzagPage = FXMLLoader.load(getClass().getResource("gameSuccess.fxml"));
				EndPopUpPage.getChildren().setAll(zigzagPage);
				System.out.println(ganswer);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
				effectMusic.start();
				zigzagPage = FXMLLoader.load(getClass().getResource("gameFail.fxml"));
				EndPopUpPage.getChildren().setAll(zigzagPage);
				System.out.println(ganswer);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
