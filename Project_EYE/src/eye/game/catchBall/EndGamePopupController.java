package eye.game.catchBall;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.jfoenix.controls.JFXButton;

import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndGamePopupController implements Initializable {

	@FXML
	private Button goMainButton, backGame;

	@FXML
	private Label gameTime;

	@FXML
	private Label gameScore;

	@FXML
	void backGameAction(ActionEvent event) {

		try {
			Parent CatchballGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml"));
			Scene scene = new Scene(CatchballGame);

			// css
			scene.getStylesheets()
					.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());

			CatchballGameController.currentStage.setScene(scene);
			CatchballGameController.currentStage.setTitle("CatchballGame");

			Stage primaryStage = (Stage) backGame.getScene().getWindow(); // ���� ������ ��������
			primaryStage.close();

			CatchballGameController.bigScore = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goMainButtonAction(ActionEvent event)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {

		Main.setMusic("mainMusic", true);
		// go main
		try {
			Parent selectSpeedPage = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
			Scene scene = new Scene(selectSpeedPage);

			// css
			scene.getStylesheets()
					.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());

			Stage primaryStage = (Stage) backGame.getScene().getWindow(); // ���� ������ ��������

			CatchballGameController.bigScore = 0;

			CatchballGameController.currentStage.setScene(scene);
			CatchballGameController.currentStage.setTitle("game_main_page");

			primaryStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gameScore.setText(String.valueOf(CatchballGameController.bigScore));
		gameTime.setText(String.valueOf(CatchballGameController.timeTime));
	}

}
