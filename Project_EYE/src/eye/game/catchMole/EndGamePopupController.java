package eye.game.catchMole;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import eye.db.*;
public class EndGamePopupController implements Initializable {

	dbconn db = new dbconn();
	
	@FXML
	private Button backGame;

	@FXML
	private Button goMainButton;

	@FXML
	private Label gameScore,first,second,third;

	@FXML
	void backGameAction(ActionEvent event) {

		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Stage primaryStage = (Stage) backGame.getScene().getWindow();
			primaryStage.close();
			Parent DodugeGame2 = FXMLLoader.load(getClass().getResource("DodugeGame2.fxml"));
			Scene scene = new Scene(DodugeGame2);

			scene.getStylesheets()
					.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
			DodugeGameController.score = 0;
			DodugeGameController.timeTime = 60;
			DodugeGameController.dodugeStage.setResizable(false);
			DodugeGameController.dodugeStage.setScene(scene);
			DodugeGameController.dodugeStage.setTitle("DodugeGame");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goMainButtonAction(ActionEvent event)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();
		Main.setMusic("mainMusic", true, 1);
		// go main
		try {
			setController.isGameStart = false;
			DodugeGameController.score = 0;
			Parent selectSpeedPage = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
			Scene scene = new Scene(selectSpeedPage);
			DodugeGameController.dodugeStage.setResizable(false);
			DodugeGameController.dodugeStage.setScene(scene);
			DodugeGameController.dodugeStage.setTitle("game_main_page");

			scene.getStylesheets()
					.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());

			Stage primaryStage = (Stage) backGame.getScene().getWindow();

			primaryStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gameScore.setText(String.valueOf(DodugeGameController.score));
		Double recordData[] = null;
		try {
			recordData = db.getGameData("catchMole",1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(recordData));
		if(recordData[0]!=null)
			first.setText("1위 : "+Double.toString(recordData[0])+"점");
		else
			first.setText("1위 : ");
		if(recordData[1]!=null)
			second.setText("2위 : "+Double.toString(recordData[1])+"점");
		else
			second.setText("2위 : ");
		if(recordData[2]!=null)
			third.setText("3위 : "+Double.toString(recordData[2])+"점");
		else
			third.setText("3위 : ");
	}

}
