package eye.game.findPicture;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EndPopupController implements Initializable {

	@FXML
	private Button goMainButton, backGame;
	
	@FXML
	AnchorPane endPopup;

	@FXML
	private Label gameScore;

	@FXML
	void backGameAction(ActionEvent event) {
		try {

			Stage primaryStage = (Stage) backGame.getScene().getWindow();
			primaryStage.close();
			Parent DodugeGame2 = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
			Scene scene = new Scene(DodugeGame2);
		
			scene.getStylesheets()
			.add(getClass().getResource("../../main/controller/application.css").toExternalForm());
			
			GamePageController.bigScore = 0;
			GamePageController.timeTime = 60;
			GamePageController.dodugeStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goMainButtonAction(ActionEvent event)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		Main.mainMusic.stopMusic();
		Main.mainMusic.resetNameAudioStream("mainMusic");
		// go main
		try {
			GamePageController.bigScore = 0;
			Parent selectSpeedPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
			Scene scene = new Scene(selectSpeedPage);
			GamePageController.dodugeStage.setScene(scene);
			GamePageController.dodugeStage.setTitle("game_main_page");

			scene.getStylesheets()
					.add(getClass().getResource("../../main/controller/application.css").toExternalForm());

			Stage primaryStage = (Stage) backGame.getScene().getWindow();

			primaryStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gameScore.setText(String.valueOf(GamePageController.bigScore));
	}
	
}
